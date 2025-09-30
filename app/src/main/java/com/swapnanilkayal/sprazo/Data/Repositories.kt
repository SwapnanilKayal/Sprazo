package com.swapnanilkayal.sprazo.Data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Repositories {
    val h = mutableStateListOf<Int>(10,50)

    private const val scorePrefName = "sprazo_scores"
    private const val streakPrefName = "sprazo_streak"
    private const val inventoryPrefName = "sprazo_inventory"
    private lateinit var scorePref: SharedPreferences
    private lateinit var streakPref: SharedPreferences
    private lateinit var inventoryPref: SharedPreferences
    lateinit var scoreRepository: ScoreRepository
    lateinit var streakRepository: StreakRepository
    lateinit var inventoryRepository: Inventory
    fun init(context: Context) {
        scorePref = context.getSharedPreferences(scorePrefName, Context.MODE_PRIVATE)
        scoreRepository = ScoreRepository(scorePref)
        inventoryPref = context.getSharedPreferences(inventoryPrefName, Context.MODE_PRIVATE)
        inventoryRepository = Inventory(inventoryPref)
        streakPref = context.getSharedPreferences(streakPrefName, Context.MODE_PRIVATE)
        streakRepository = StreakRepository(streakPref, inventoryRepository)
    }
}