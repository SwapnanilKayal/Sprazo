package com.swapnanilkayal.sprazo.Models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.Lesson
import com.swapnanilkayal.sprazo.Data.Repositories
import com.swapnanilkayal.sprazo.System.Prefs

data class LockID(
    val lessonId:String,
    val unitId:String,
    val sectionId: String,
    val difficulty: Int,
    val navToLesson: (String) -> Unit
)

class HomeViewModel(): ViewModel() {
    var isSectionChooserOpen by mutableStateOf(false)
    var lockedSessionsData by mutableStateOf<LockID?>(null)


    fun navigateToLesson(sectionId:String, unitId:String, lessonId:String, difficulty:Int, navToLesson: (String) -> Unit){
        val id = "lesson/${sectionId.replace("/",".")}/${unitId.replace("/",".")}/${lessonId.replace("/",".")}/$difficulty"
        Log.d("HomeViewModel", "Opening lesson: $sectionId, $unitId, $lessonId, $difficulty\n ID: $id")
        navToLesson(id)
    }
    fun openLesson(sectionId:String, unitId:String, lessonId:String, difficulty:Int, navToLesson: (String) -> Unit){
        if (Repositories.scoreRepository.isLocked(sectionId,unitId,lessonId)){
            lockedSessionsData = LockID(lessonId,unitId,sectionId,difficulty,navToLesson)
        } else {
            try {
                navigateToLesson(sectionId,unitId,lessonId,difficulty,navToLesson)
            } catch (e: Exception){}
        }
    }

    fun openLesson(lockID: LockID, navToLesson: (String) -> Unit){
        navigateToLesson(lockID.sectionId, lockID.unitId, lockID.lessonId, lockID.difficulty, navToLesson)
        lockedSessionsData = null
    }

}

