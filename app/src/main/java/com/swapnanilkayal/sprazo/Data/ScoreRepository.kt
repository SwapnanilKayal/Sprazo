package com.swapnanilkayal.sprazo.Data

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.core.content.edit
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Lessons
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Units
import com.swapnanilkayal.sprazo.Data.Course.getLessonUID

object ScoreKeys{
    const val SCORE = "SCORE"
}
class ScoreRepository(private val scorePref: SharedPreferences) {

    private val scoreMap = mutableStateMapOf<String, Int>()
    private val lessonIndexRMap = mutableMapOf<String, Int>()
    private val lessonUids = mutableListOf<String>()

    init {
        val sMap = Sections.associateBy { it.id }
        val uMap = Units.associateBy { it.id }
        val lMap = Lessons.associateBy { it.id }
        var imap = 0
        sMap.entries.forEach { (sectionId, section) ->
            section.Units.map {Pair(it,uMap[it]!!)}.forEach {(unitId,unit)->
                unit.lessonIDs.map {Pair(it,lMap[it])}.forEach {(lessonId,lesson)->
                    val scoreUID =  getLessonUID(lessonId,unitId,sectionId, ScoreKeys.SCORE)
                    lessonIndexRMap.put(scoreUID,imap++)
                    lessonUids.add(scoreUID)
                    scoreMap.put(scoreUID,get(scoreUID)?:0)
                }
            }
        }
    }

    fun flush(){
        scoreMap.forEach { (key, value) ->
            if (value > 0){
                scorePref.edit { putInt(key, value) }
            }
        }
    }

    operator fun get(key: String): Int? {
        Log.d("ScoreRepository", "Getting score for $key")
        if (scoreMap.containsKey(key)) {
            Log.d("ScoreRepository", "Getting score for $key from map")
            return scoreMap[key]
        }
        if (scorePref.contains(key)) {
            Log.d("ScoreRepository", "Getting score for $key from preferences")
            val stored = scorePref.getInt(key, 0)
            scoreMap[key] = stored
            return stored
        }

        return null
    }

    operator fun set(key: String, value: Int) {
        Log.d("ScoreRepository", "Setting score for $key to $value")
        scoreMap[key] = value
        scorePref.edit { putInt(key, value) }
    }

    fun isLocked(sectionId: String, unitId: String, lessonId: String): Boolean {
        val scoreUID = getLessonUID(lessonId,unitId,sectionId, ScoreKeys.SCORE)
        val rIndex = lessonIndexRMap[scoreUID]
        if (rIndex == null) return true
        else if (rIndex == 0) return false
        else return ((scoreMap[lessonUids[rIndex-1]]?:0)+ (scoreMap[lessonUids[rIndex]]?:0)) <= 0
    }

    fun staticGet(key: String):Int?{
        return scorePref.getInt(key,0)
    }

    fun staticScoreLesson() = 0

    fun asStateMap(): SnapshotStateMap<String, Int> = scoreMap
    fun asMap(): Map<String, Int> = scoreMap.toMap()
}