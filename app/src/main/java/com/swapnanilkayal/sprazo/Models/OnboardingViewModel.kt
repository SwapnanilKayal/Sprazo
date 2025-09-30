package com.swapnanilkayal.sprazo.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swapnanilkayal.sprazo.System.Prefs

class OnboardingViewModel(): ViewModel(){
    var pageCount by mutableStateOf(6)
    var page by mutableStateOf(0)
    var localPagePass =  mutableStateListOf(true,true,false,false,false,true)
    var pageNames = mutableListOf("Welcome","License","Proficiency","Difficulty","Introduction","Ready?")
    var mode by mutableStateOf("setup")

    init {
        selectSkillLevel(Prefs.getSkillLevel())
        updateUname(Prefs.getUserName())
    }

    fun updateUname(uname: String) {
        Prefs.Reactive.userName = if (uname.equals("guest", true)) "" else uname
        localPagePass[4] = Prefs.Reactive.userName.trim().isNotEmpty()
    }
    fun selectSkillLevel(level:Int){
        if (Prefs.Reactive.skillLevel != level){
            Prefs.Reactive.skillLevel = level
            localPagePass[2] = true
        }
        else {
            Prefs.Reactive.skillLevel = -1
            localPagePass[2] = false
        }
    }

    fun nextPage(onFinish: () -> Unit){
        if (page == pageCount-1) {
            savePreferences()
            onFinish()
            return
        }
        if (localPagePass[page]) page++
    }

    fun isNextEnabled():Boolean = localPagePass[page]

    fun prevPage(){
        if (page > 0) page--
    }

    fun isPrevEnabled():Boolean = page > 0

    fun savePreferences(){
        Prefs.Reactive.isOnBoarded = true
        Prefs.Reactive.synctoPref()
    }
    fun setDifficulty(difficulty: Int){
        Prefs.Reactive.difficulty = if (difficulty != Prefs.Reactive.difficulty) difficulty else -1
        localPagePass[3] = Prefs.Reactive.difficulty >= 0
    }
}
