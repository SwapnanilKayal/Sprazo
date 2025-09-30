package com.swapnanilkayal.sprazo.Data

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.edit

/*
class Inventory(private val inventoryPref: SharedPreferences) {
    private val inventoryMap = mutableStateMapOf<String, Int>()
    private var dirty = false

    init {

        inventoryPref.all.forEach { (key, value) ->
            if (value is Int) {
                inventoryMap[key] = value
            }
        }
    }

    operator fun get(key: String): Int? = inventoryMap[key]

    operator fun set(key: String, value: Int) {
        if (inventoryMap[key] != value) {
            inventoryMap[key] = value
            dirty = true
        }
    }

    /** Persist pending changes to SharedPreferences */
    fun flush() {
        if (!dirty) return
        inventoryPref.edit {
            inventoryMap.forEach { (key, value) ->
                putInt(key, value)
            }
        }
        dirty = false
    }


    fun getGemCount(): Int = get("GEMS") ?: 0
    fun getStreakFreezes(): Int = get("STREAK_FREEZES") ?: 0
    fun getXP(): Int = get("XP") ?: 0

    fun setStreakFreezes(value: Int) = set("STREAK_FREEZES", value)
    fun setGemCount(value: Int) = set("GEMS", value)
    fun setXP(value: Int) = set("XP", value)

    fun addXP(value: Int) = setXP(getXP() + value)

    fun useStreakFreeze() = setStreakFreezes(getStreakFreezes() - 1)

    fun useGem(count: Int): Boolean {
        val gemCount = getGemCount()
        return if (gemCount >= count) {
            setGemCount(gemCount - count)
            true
        } else false
    }
}
*/
class Inventory(private val inventoryPref: SharedPreferences) {
    private val inventoryMap = mutableStateMapOf<String, Int>()
    private var dirty = false

    init {
        inventoryPref.all.forEach { (key, value) ->
            if (value is Int) {
                inventoryMap[key] = value
            }
        }
    }

    operator fun get(key: String): Int? = inventoryMap[key]

    operator fun set(key: String, value: Int) {
        if (inventoryMap[key] != value) {
            inventoryMap[key] = value
            dirty = true
        }
    }

    /** Persist pending changes to SharedPreferences */
    fun flush() {
        if (!dirty) return
        inventoryPref.edit {
            inventoryMap.forEach { (key, value) ->
                putInt(key, value)
            }
        }
        dirty = false
    }

    fun getGemCount(): Int = get("GEMS") ?: 0
    fun getStreakFreezes(): Int = get("STREAK_FREEZES") ?: 0
    fun getXP(): Int = get("XP") ?: 0

    fun setStreakFreezes(value: Int) = set("STREAK_FREEZES", value)
    fun setGemCount(value: Int) = set("GEMS", value)
    fun addGems(value: Int) = setGemCount(getGemCount() + value)
    fun setXP(value: Int) = set("XP", value)

    fun addXP(value: Int) = setXP(getXP() + value)

    fun useStreakFreeze() = setStreakFreezes(getStreakFreezes() - 1)

    fun useGem(count: Int): Boolean {
        val gemCount = getGemCount()
        return if (gemCount >= count) {
            setGemCount(gemCount - count)
            true
        } else false
    }
}
