package com.swapnanilkayal.sprazo.Models
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Lessons
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Units
import com.swapnanilkayal.sprazo.Data.Course.LSection
import com.swapnanilkayal.sprazo.Data.Course.LUnit
import com.swapnanilkayal.sprazo.Data.Course.Lesson
import com.swapnanilkayal.sprazo.Data.Course.Option
import com.swapnanilkayal.sprazo.Data.Course.QuestionBank
import com.swapnanilkayal.sprazo.Data.Course.getLessonMaxScore
import com.swapnanilkayal.sprazo.Data.Course.getLessonUID
import com.swapnanilkayal.sprazo.Data.Difficulty
import com.swapnanilkayal.sprazo.Data.Repositories
import com.swapnanilkayal.sprazo.Data.StreakTypes
import com.swapnanilkayal.sprazo.Data.difficulties
import java.time.LocalDate
object Phases {
    const val LESSON_START = 0
    const val LESSON_ONGOING = 1
    const val LESSON_MISTAKE_ACKNOWLEDGEMENT = 2
    const val LESSON_MISTAKE_REVIEW = 3
    const val LESSON_COMPLETE = 4
}
object Result {
    const val PASS = 0
    const val FAIL = 1
    const val INCOMPLETE = 2
}
class QuestionState(val questionId:String, val lessonId: String, val unitId: String, val sectionId: String, val difficulty: Difficulty, val diffOrdinal:Int,val model: LessonViewModel){
    companion object {
        object OutputTypes {
            const val IMAGE = "IMAGE"
            const val AUDIO = "AUDIO"
            const val TEXT = "TEXT"
        }
        object InputTypes{
            const val OPTIONS = "OPTIONS"
            const val WORD_CHOOSE = "WORD_CHOOSE"
            const val TEXT = "TEXT"
            const val SPEAK = "SPEAK"
        }
    }
    var question = QuestionBank.questions.find { it.id == questionId }
    var qTxt: String? = null
    var qImg: Int? = null
    var qAud: String? = null
    var pTxt:Pair<String,String>? = null
    var defaultWordPool = mutableListOf<String>()
    var wordPool = mutableStateListOf<String>()
    var answers: List<String>? = null
    var options: List<Option>? = null
    var inputType: String? = null
    var selectedOption by mutableStateOf<Option?>(null)
    val wordChosen = mutableStateListOf<String>()
    var textInput by mutableStateOf("")
    var sessionTriesLeft by mutableStateOf(2)
    var TriesLeft by mutableStateOf(2)
    var ResultState by mutableStateOf(Result.INCOMPLETE)
    var summary by mutableStateOf("")
    init {
        question?.let {
            qTxt = it.questionString
            pTxt = if (qTxt?.contains("__")?:false) qTxt?.split("__")?.let { it[0] to it[1] } else null
            qImg = it.questionImage
            qAud = it.questionAudio
            answers = it.answers
            options = it.options
            defaultWordPool.addAll(it.rWordPools?.get(diffOrdinal.coerceAtMost(it.rWordPools.size-1))?:listOf())
            defaultWordPool.addAll(answers?.get(0)?.split(" ")?:listOf())
            defaultWordPool.shuffle()
            wordPool.addAll(defaultWordPool)
        }
        if (options != null && question?.options?.isNotEmpty()?:false){
            inputType = InputTypes.OPTIONS
        }
        else if (wordPool.isNotEmpty()){
            inputType = InputTypes.WORD_CHOOSE
        }
        else if (questionId.contains("SPK")) {
            inputType = InputTypes.SPEAK
        }
        else if (qTxt != null){
            inputType = InputTypes.TEXT
        }
    }
    fun takeInput(input: String){
        if (inputType == InputTypes.WORD_CHOOSE){
            if (wordPool.contains(input)){
                wordChosen.add(input)
                wordPool.remove(input)
            } else if (wordChosen.contains(input)){
                wordChosen.remove(input)
                wordPool.add(defaultWordPool.indexOf(input) ,input)
            }
        } else if (inputType == InputTypes.TEXT){
            textInput = input
        }
    }
    fun takeInput(input: Option){
        if (inputType == InputTypes.OPTIONS){
            if (selectedOption == input){
                selectedOption = null
            } else selectedOption = input
        }
    }
    fun getOutput():String{
        if (inputType == InputTypes.OPTIONS){
            return selectedOption?.string?:""
        } else if (inputType == InputTypes.WORD_CHOOSE){
            return wordChosen.joinToString(" ")
        } else if (inputType == InputTypes.TEXT){
            return textInput
        }
        return ""
    }
    fun typoAwareWordCheck(c: Pair<String, String>): Pair<String,Float>{
        val word = c.first.trim()
        val correctAnswer = c.second.trim()
        if (word == correctAnswer) return word to 1.0f
        var confidence = 0f
        if (word.length == correctAnswer.length) confidence += 0.2f
        val lack = 1 - confidence
        val confMultiplier = lack/correctAnswer.length
        for (i in 0 until minOf(word.length,correctAnswer.length)){
            if (word[i] == correctAnswer[i]) confidence += confMultiplier
            else if (word[i].lowercase() == correctAnswer[i].lowercase()) confidence += confMultiplier*0.5f
        }
        return word to confidence.coerceAtMost(1f)
    }
    fun typoAwareStringCheck(text:String, correctAnswer:String): Triple<List<String>,List<Pair<String,Int>>, Float>{
        val segText = text.split(" ").map { it.trim()}.filter { it.isNotBlank() }
        val segAnswer = correctAnswer.split(" ").map { it.trim() }.filter { it.isNotBlank() }
        if (text.trim() == correctAnswer.trim()) return Triple(segText, listOf(), 1.0f)
        val typoTrack = mutableListOf<Pair<String,Int>>()
        val maxLength = minOf(segText.size,segAnswer.size)
        var tConfidence = 0f
        for (i in 0 until maxLength){
            val (word,confidence) = typoAwareWordCheck(segText[i] to segAnswer[i])
            tConfidence += confidence
            if (confidence < difficulty.typoConfidenceThreshold) typoTrack.add(word to i)
        }
        return Triple(segText,typoTrack,tConfidence/maxLength)
    }
    fun checkText(text: String): Boolean{
        val typoArr = mutableListOf<Triple<List<String>,List<Pair<String,Int>>, Float>>()
        answers?.forEach {
            typoArr.add(typoAwareStringCheck(text,it))
        }
        val fuzzyAnswer = typoArr.maxBy { it.third }
        val (segText,typoTrack,confidence) = fuzzyAnswer
        summary = ""
        val rState: Boolean = confidence >= difficulty.typoConfidenceThreshold
        if (confidence == 1.0f){
            summary = "Correct"
        } else {
            if (rState) {
                summary = "Correct with typos"
            } else {
                summary = "Incorrect"
            }
        }
        if (typoTrack.isNotEmpty()){
            summary += "\n Typos: \n"
            typoTrack.forEach { (correctWord,pos) ->
                summary += "\n ${segText[pos]} in place of $correctWord"
            }
        }
        return rState
    }
    fun canCheck(): Boolean{
        if (inputType == InputTypes.OPTIONS){
            return selectedOption != null
        } else if (inputType == InputTypes.WORD_CHOOSE){
            return wordChosen.isNotEmpty()
        } else if (inputType == InputTypes.TEXT){
            return textInput.isNotBlank()
        }
        return false
    }
    fun setResult(res:Int){
        if (res == ResultState) return
        val lifeCost = difficulty.getLifeCost(model.livesLeft)
        val maxLives = difficulty.getMaxLifeCount(Repositories.streakRepository.streakCount)
        if (res == Result.FAIL){
            if (!difficulty.getKeepEssenceOnFail(model.livesLeft)) model.lifeEssence = 0f
            if (maxLives > 0 && model.livesLeft > 0) model.livesLeft--
        }
        else if (res == Result.PASS){
            model.lifeEssence = (model.lifeEssence + if (lifeCost >0) 1.0f/lifeCost else 1.0f).coerceAtMost(1f)
        }
        ResultState = res
    }
    fun checkAnswer(autoSubmit:Boolean = false){
        if (inputType == InputTypes.OPTIONS){
            if (selectedOption?.isCorrect?:false) setResult(Result.PASS)
            else if (!autoSubmit) setResult(Result.FAIL)
        } else if (inputType == InputTypes.WORD_CHOOSE){
            if (answers?.contains(wordChosen.joinToString(" ")) == true ) setResult(Result.PASS)
            else if (!autoSubmit) setResult(Result.FAIL)
        }
        else if (inputType == InputTypes.TEXT){
            if (checkText(textInput)) setResult(Result.PASS)
            else if (!autoSubmit) setResult(Result.FAIL)
        }
    }
    fun getPass(): Boolean = ResultState == Result.PASS
    fun retry(){
        ResultState = Result.INCOMPLETE
        TriesLeft = 2
        textInput = ""
        selectedOption = null
        wordChosen.clear()
        wordPool.clear()
        question?.let { wordPool.addAll(it.rWordPools?.get(diffOrdinal.coerceAtMost(it.rWordPools.size-1))?:listOf()) }
        wordPool.addAll(answers?.get(0)?.split(" ")?:listOf())
        wordPool.shuffle()
        summary = ""
    }
    override fun hashCode(): Int {return questionId.hashCode()}
    override fun equals(other: Any?): Boolean {
        if (other is QuestionState){
            return other.questionId == questionId
        } else return false
    }
}
class LessonViewModel(val sectionId:String, val unitId: String, val lessonId: String, val difficulty: Int): ViewModel() {
    val lessonDifficulty = difficulties[difficulty]
    var livesLeft by mutableStateOf(lessonDifficulty.getMaxLifeCount(Repositories.streakRepository.streakCount))
    var lifeEssence by mutableStateOf(0f)
    var phase by mutableStateOf(0)
    var questionNumber by mutableStateOf(0)
    val questionCount:Int
    val lesson: Lesson?
    val section: LSection?
    val unit: LUnit?
    var netScore = 0
    var earnedGems by mutableStateOf(0)
    var xpEarned by mutableStateOf(0)
    var mistakesCorrected by mutableStateOf(0)
    var mistakesMade = 0
    var loadedQuestions: List<QuestionState>
    var mistakenQuestions = mutableListOf<Int>()
    val correctQuestions = mutableListOf<Int>()
    val PATH_OK: Boolean
    init {
        lesson = Lessons.find { it.id == lessonId }
        section = Sections.find { it.id == sectionId }
        unit = Units.find { it.id == unitId }
        if (lesson != null && section != null && unit != null){
            PATH_OK = true
            questionCount = lesson.questionIDs.size
        }
        else{
            PATH_OK = false
            questionCount = 0
        }
        loadedQuestions = lesson?.questionIDs?.map { QuestionState(it,lessonId,unitId,sectionId,lessonDifficulty,difficulty, this) }?: listOf()
        assert(loadedQuestions.size == questionCount)
    }
    fun reachCompletion(){
        netScore = loadedQuestions.filter { correctQuestions.map {idx -> loadedQuestions[idx]}.contains(it) }.sumOf { it.question?.points?:0 }
        xpEarned = lessonDifficulty.getXpEarned((netScore/ getLessonMaxScore(lessonId)).toDouble()).coerceAtLeast(0)
        earnedGems = lessonDifficulty.getGemReward(xpEarned,mistakesMade).coerceAtLeast(0)
        Repositories.streakRepository.placeStreak(LocalDate.now(), StreakTypes.STREAK_CONTINUE)
        Repositories.inventoryRepository.addXP(xpEarned)
        Repositories.inventoryRepository.addGems(earnedGems)
        Repositories.scoreRepository[getLessonUID(lessonId,unitId,sectionId,"SCORE")] = netScore
        phase = Phases.LESSON_COMPLETE
    }
    fun next(){
        val maxLife = lessonDifficulty.getMaxLifeCount(Repositories.streakRepository.streakCount)
        if (lifeEssence > 0.9f && maxLife > 0 && livesLeft < maxLife) {
            livesLeft++
            lifeEssence = 0f
        }
        if (livesLeft == 0) {
            reachCompletion()
            return 
        }
        when (phase){
            Phases.LESSON_START -> {
                phase = Phases.LESSON_ONGOING
            }
            Phases.LESSON_ONGOING -> {
                if (questionNumber < questionCount-1){
                    val isCurrentQuestionPassed = loadedQuestions[questionNumber].getPass()
                    if (isCurrentQuestionPassed) {
                        correctQuestions.add(questionNumber)
                    }
                    else {
                        mistakenQuestions.add(questionNumber)
                        mistakesMade++
                    }
                    questionNumber++
                } else{
                    if (mistakenQuestions.isNotEmpty()) {
                        phase = Phases.LESSON_MISTAKE_ACKNOWLEDGEMENT
                    }
                    else {
                        reachCompletion()
                    }
                }
            }
            Phases.LESSON_MISTAKE_ACKNOWLEDGEMENT -> {
                mistakenQuestions.forEach {
                    loadedQuestions[it].retry()
                }
                if (mistakenQuestions.isEmpty()) {
                    reachCompletion()
                    return
                }
                else {
                    questionNumber = mistakenQuestions[0]
                    phase = Phases.LESSON_MISTAKE_REVIEW
                }
            }
            Phases.LESSON_MISTAKE_REVIEW -> {
                val current = loadedQuestions[questionNumber]
                current.TriesLeft--
                val isReviewPassed = current.getPass()
                if (isReviewPassed) {
                    correctQuestions.add(questionNumber)
                    mistakenQuestions.remove(questionNumber)
                    mistakesCorrected++
                } else {
                    mistakesMade++
                }
                val oldMistakenQuestions = mistakenQuestions.joinToString()
                mistakenQuestions = mistakenQuestions
                    .filter {
                        val triesLeft = loadedQuestions[it].TriesLeft
                        val keep = triesLeft > 0
                        
                        keep
                    }
                    .sortedBy { it }
                    .toMutableList()
                if (mistakenQuestions.isEmpty()) {
                    
                    reachCompletion()
                    return
                }
                val qNext = mistakenQuestions.firstOrNull { it > questionNumber }
                
                questionNumber = qNext ?: mistakenQuestions.first()
                
            }
        }
    }}
class LessonViewModelFactory(val sectionId:String, val unitId: String, val lessonId: String, val difficulty: Int): Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonViewModel::class.java)){
            return LessonViewModel(sectionId,unitId,lessonId,difficulty) as T
        } else throw Exception("Unknown ViewModel")
    }
}