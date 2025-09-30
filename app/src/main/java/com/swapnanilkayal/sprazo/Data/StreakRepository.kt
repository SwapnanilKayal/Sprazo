package com.swapnanilkayal.sprazo.Data

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.core.content.edit
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object StreakTypes {
    const val STREAK_CONTINUE = 0
    const val STREAK_FREEZE = 1
    const val STREAK_NONE = -1
}

class StreakRepository(
    private val streakPref: SharedPreferences,
    private val inventory: Inventory
) {
    private val streakMap: SnapshotStateMap<String, Int> = mutableStateMapOf()
    var streakCount by mutableStateOf(0)
        private set
    var streakToday by mutableStateOf(false)
        private set

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    init {
        updateStreakData()
    }

    private fun frozenStreakApplier(lastStreak: LocalDate): LocalDate {
        var current = lastStreak
        while (ChronoUnit.DAYS.between(current, LocalDate.now()) > 1) {
            if (inventory.getStreakFreezes() > 0) {
                inventory.useStreakFreeze()
                current = current.plusDays(1)
                placeStreak(current, StreakTypes.STREAK_FREEZE)
            } else break
        }
        inventory.flush()
        return current
    }
    fun updateStreakData() {
        val lastStreak = getLastStreak()
        if (lastStreak == null) {
            streakCount = 0
            streakToday = false
            return
        }

        var effectiveLast = lastStreak
        val gap = ChronoUnit.DAYS.between(effectiveLast, LocalDate.now())

        if (gap > 1) {
            effectiveLast = frozenStreakApplier(effectiveLast)
        }

        val newGap = ChronoUnit.DAYS.between(effectiveLast, LocalDate.now())
        if (newGap > 1) {
            setSC(0)
            streakToday = false
        } else {
            streakCount = getSC()
            streakToday = newGap == 0L
        }
    }

    fun getSC(): Int = streakPref.getInt("STREAK_COUNT", 0)
    fun setSC(value: Int) {
        streakPref.edit { putInt("STREAK_COUNT", value) }
        streakCount = value
    }
    fun incrementStreakCount() = setSC(getSC() + 1)
    fun decrementStreakCount() = setSC(getSC() - 1)

    fun hasStreak(day: LocalDate): Int? = get(day.format(formatter))

    fun placeStreak(day: LocalDate, type: Int) {
        val today = LocalDate.now()
        val lastStreak = getLastStreak()
        val currentState = hasStreak(day)

        if (today.isEqual(day)) streakToday = true

        if (currentState == null){
            if (lastStreak == null || ChronoUnit.DAYS.between(lastStreak, day) > 0) {
                setLastStreak(day)
            }
            if (type == StreakTypes.STREAK_CONTINUE) {
                incrementStreakCount()
            }
            set(day.format(formatter), type)
        }
        else {
            if (type == StreakTypes.STREAK_CONTINUE) {
                if (currentState == StreakTypes.STREAK_FREEZE) {
                    incrementStreakCount()
                    inventory.setStreakFreezes(inventory.getStreakFreezes()+1)
                    set(day.format(formatter), type)
                }
            }
            else {
                if (currentState == StreakTypes.STREAK_CONTINUE) {
                    decrementStreakCount()
                    if (inventory.getStreakFreezes() > 0) inventory.useStreakFreeze()
                    set(day.format(formatter), type)
                }
            }
        }
        inventory.flush()
    }

    fun getLastStreak(): LocalDate? =
        streakPref.getString("LAST_STREAK", null)?.let {
            LocalDate.parse(it, formatter)
        }

    private fun setLastStreak(value: LocalDate) {
        streakPref.edit { putString("LAST_STREAK", value.format(formatter)) }
    }

    operator fun get(key: String): Int? {
        streakMap[key]?.let { return it }

        if (streakPref.contains(key)) {
            val stored = streakPref.getInt(key, StreakTypes.STREAK_NONE)
            return if (stored == StreakTypes.STREAK_CONTINUE || stored == StreakTypes.STREAK_FREEZE) {
                streakMap[key] = stored
                stored
            } else null
        }
        return null
    }

    operator fun set(key: String, value: Int) {
        streakMap[key] = value
        streakPref.edit { putInt(key, value) }
    }

    fun asStateMap(): SnapshotStateMap<String, Int> = streakMap
    fun asMap(): Map<String, Int> = streakMap.toMap()
}
