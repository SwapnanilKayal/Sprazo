package com.swapnanilkayal.sprazo.Models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swapnanilkayal.sprazo.Data.Difficulty
import com.swapnanilkayal.sprazo.Data.Repositories
import com.swapnanilkayal.sprazo.Data.difficulties

class StoreViewModel(val difficultyId: Int) : ViewModel(){
    val difficulty = difficulties[difficultyId]
    var reason by mutableStateOf<String?>(null)
    fun getFrozenStreakPrice() = difficulty.getFrozenStreakCost(Repositories.streakRepository.streakCount)
    fun getFrozenStreakAmount() = Repositories.inventoryRepository.getStreakFreezes()
    fun canPurchaseFrozenStreak(): Boolean{
        if (getFrozenStreakPrice() <= Repositories.inventoryRepository.getGemCount()) reason = "Too Pricey!"
        else if (getFrozenStreakAmount() >= difficulty.getMaxFrozenStreakCount(Repositories.streakRepository.streakCount)) reason = "Too Many!"
        else reason = null
        return reason!=null
    }
    fun purchaseFrozenStreak(){
        if (canPurchaseFrozenStreak()){
            Repositories.inventoryRepository.useGem(getFrozenStreakPrice())
            Repositories.inventoryRepository.setStreakFreezes(getFrozenStreakAmount()+1)
        }
    }

    init {
        canPurchaseFrozenStreak()
    }



}

class StoreViewModelFactory(val difficulty: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            return StoreViewModel(difficulty) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}