package com.swapnanilkayal.sprazo.Data.Course

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Lessons
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Units
import com.swapnanilkayal.sprazo.Data.Repositories


fun getLessonUID(lessonID:String, unitID:String, SectionID:String, type:String):String{
    return "$type/$SectionID/$unitID/$lessonID"
}


data class Option(val string:String, val audio:String?, val image: Int?, var isCorrect:Boolean)



data class Question(val id: String, val points:Int, val questionString:String?, val questionAudio:String?, val questionImage: Int?, val answers: List<String>?, val rWordPools:List<List<String>>?, val options:List<Option>, val showQuestionString: Boolean, val focusTopic: String, val allowAutoSubmit:Boolean = false)


object QuestionTypes{
    val MCQ = "MCQ"
    val FTB = "FTB"
    val LISTEN = "LSTN"
    val SPEAK = "SPK"
    val RECOGNIZE = "RCGN"
    val TRN_DE_EN = "TRN_DE_EN"
    val TRN_EN_DE = "TRN_EN_DE"
}

data class Lesson(
    val id: String,
    val title:String,
    val questionIDs: MutableList<String>,
    val concept:String,
){
    var score = mutableStateOf(null)
}


data class LUnit(val id:String, val name:String, val lessonIDs: SnapshotStateList<String>)
data class LSection(val id:String, val name: String, val Units: SnapshotStateList<String>)


fun getQuestionMaxScore(id: String): Int = QuestionBank.questions.find { it.id == id }?.points?:1

fun getLessonMaxScore(id:String):Int = Lessons.find { it.id == id }?.questionIDs?.sumOf { getQuestionMaxScore(it) }?:0
fun getUnitMaxScore(id:String):Int = Units.find { it.id == id }?.lessonIDs?.sumOf { getLessonMaxScore(it) }?:0
fun getSectionMaxScore(id:String):Int = Sections.find { it.id == id }?.Units?.sumOf { getUnitMaxScore(it) }?:0

fun getLessonScore(lessonID:String, unitID: String, sectionID:String):Int = Repositories.scoreRepository[getLessonUID(lessonID,unitID,sectionID,"SCORE")]?:0
fun getUnitScore(unitID: String, sectionID:String):Int = Units.find { it.id == unitID }?.lessonIDs?.sumOf { getLessonScore(it,unitID,sectionID) }?:0
fun getSectionScore(sectionID:String):Int = Sections.find { it.id == sectionID }?.Units?.sumOf { getUnitScore(it,sectionID) }?:0

fun getUnitProgress(unitID: String, sectionID:String): Double {
    val unitScore = getUnitScore(unitID, sectionID)
    val maxScore = getUnitMaxScore(unitID)
    Log.d("unitProgress|Score", "unitScore: $unitScore, maxScore: $maxScore")
    return unitScore * 100.0 / maxScore
}

fun getLessonProgress(sectionID: String, unitID: String, lessonID: String) = getLessonScore(lessonID, unitID, sectionID) / getLessonMaxScore(lessonID)

/*
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromNestedList(value: List<List<String>>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toNestedList(value: String?): List<List<String>>? {
        val listType = object : TypeToken<List<List<String>>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromOptionList(value: List<OptionEntity>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOptionList(value: String?): List<OptionEntity>? {
        val listType = object : TypeToken<List<OptionEntity>>() {}.type
        return gson.fromJson(value, listType)
    }
}

@Entity(tableName = "questions")
@TypeConverters(Converters::class)
data class QuestionEntity(
    @PrimaryKey val id: String,
    val points: Int,
    val questionString: String?,
    val questionAudio: String?,
    val questionImage: String?,
    val answers: List<String>?,
    val rWordPools: List<List<String>>?,
    val options: List<OptionEntity>,
    val showQuestionString: Boolean,
    val focusTopic: String
)

data class OptionEntity(
    val string: String,
    val audio: String?,
    val image: String?,
    val isCorrect: Boolean
)

@Entity(tableName = "lessons")
@TypeConverters(Converters::class)
data class LessonEntity(
    @PrimaryKey val id: String,
    val title: String,
    val questionIDs: List<String>,
    val concept: String,
    val score: Int?
)

@Entity(tableName = "units")
@TypeConverters(Converters::class)
data class UnitEntity(
    @PrimaryKey val id: String,
    val name: String,
    val lessonIDs: List<String>
)

@Entity(tableName = "sections")
@TypeConverters(Converters::class)
data class SectionEntity(
    @PrimaryKey val id: String,
    val name: String,
    val units: List<String>
)

 */