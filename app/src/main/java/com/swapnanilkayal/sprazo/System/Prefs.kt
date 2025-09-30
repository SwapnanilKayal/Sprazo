package com.swapnanilkayal.sprazo.System

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit

object Prefs {
    object Reactive{
        var isOnBoarded by mutableStateOf(isOnboarded())
        var skillLevel by mutableStateOf(getSkillLevel())
        var userName by mutableStateOf(getUserName())
        var theme by mutableStateOf(getTheme())
        var loadedSection by mutableStateOf(getloadedSection())
        var difficulty by mutableStateOf(getDifficulty())

        fun synctoPref(){
            setSkillLevel(skillLevel)
            setUserName(userName)
            setTheme(theme)
            setOnboarded()
            setloadedSection(loadedSection)
            setDifficulty(difficulty)
        }
        fun syncFromPref(){
            isOnBoarded = isOnboarded()
            skillLevel = getSkillLevel()
            userName = getUserName()
            theme = getTheme()
            loadedSection = getloadedSection()
        }
    }
    private const val prefName = "sprazo_prefs"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) { prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE) }
    fun setSkillLevel(level:Int){
        Reactive.skillLevel = level
        prefs.edit { putInt("skillLevel", level) }
    }
    fun setUserName(name:String){
        Reactive.userName = name
        prefs.edit { putString("userName",name) }
    }
    fun setOnboarded() {
        Reactive.isOnBoarded = true
        prefs.edit { putBoolean("isOnboarded", true) }
    }
    fun setTheme(theme:String){
        Reactive.theme = theme
        prefs.edit { putString("theme",theme) }
    }
    fun setloadedSection(page:Int){
        Reactive.loadedSection = page
        prefs.edit { putInt("loadedSection",page) }
    }

    fun setDifficulty(page: Int){
        Reactive.difficulty = page
        prefs.edit { putInt("difficulty",page) }
    }


    fun getloadedSection():Int {return prefs.getInt("loadedSection",0)}
    fun isOnboarded(): Boolean { return prefs.getBoolean("isOnboarded", false) }
    fun getUserName():String{ return  prefs.getString("userName","")?:""}
    fun getSkillLevel():Int{ return prefs.getInt("skillLevel",-1) }
    fun getSkillLevelOrNull():Int? { return prefs.getInt("skillLevel", -1).let { if (it == -1) null else it } }
    fun getTheme():String{ return prefs.getString("theme","system")?:"system" }
    fun getDifficulty():Int{ return prefs.getInt("difficulty",-1) }
}