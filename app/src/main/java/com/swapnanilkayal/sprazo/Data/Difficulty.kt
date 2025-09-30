package com.swapnanilkayal.sprazo.Data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Difficulty(
    val name: String,
    val id: String,
    val color: Color,
    val icon: ImageVector,
    val description: String,
    val xpMultiplier: Double,
    val getMaxLifeCount: (streakCount: Int) -> Int,
    val getKeepEssenceOnFail: (lifeCount: Int) -> Boolean,
    val getLifeCost: (lifeCount: Int) -> Int,
    val getXpEarned: (performance: Double) -> Int,
    val getGemReward: (xpEarned: Int, livesUsed: Int) -> Int,
    val getFrozenStreakCost: (streakCount: Int) -> Int,
    val getMaxFrozenStreakCount: (currentStreakCount: Int) -> Int,
    val getIsUnlocked: (exp: Int) -> Boolean = { true },
    val typoConfidenceThreshold: Double
)

val difficulties = listOf(
    Difficulty(
        name = "Beginner",
        id = "beginner",
        color = Color(0xFFB0E0E6),
        icon = Icons.Default.Face,
        description = "Introductory mode. Unlimited attempts, no penalties.",
        xpMultiplier = 0.8,
        getMaxLifeCount = { -1 },
        getKeepEssenceOnFail = { _ -> true },
        getLifeCost = { -1 },
        getXpEarned = { performance -> (performance * 10).toInt() },
        getGemReward = { xp, livesUsed -> xp / 2 },
        getFrozenStreakCost = { streakCount -> 10 + streakCount },
        getMaxFrozenStreakCount = { _ -> 5 },
        typoConfidenceThreshold = 0.6
    ),
    Difficulty(
        name = "Elementary",
        id = "elementary",
        color = Color(0xFFD3D3D3),
        icon = Icons.Default.CheckCircle,
        description = "Light challenge, suitable for learners with some basic knowledge.",
        xpMultiplier = 0.9,
        getMaxLifeCount = { streak -> 4 + streak / 8 },
        getKeepEssenceOnFail = { lives -> lives > 1 },
        getLifeCost = { lives -> 20 * lives },
        getXpEarned = { performance -> (performance * 90).toInt() },
        getGemReward = { xp, livesUsed -> (xp / 250) - livesUsed },
        getFrozenStreakCost = { streak -> 25 + streak * 2 },
        getMaxFrozenStreakCount = { _ -> 4 },
        typoConfidenceThreshold = 0.7
    ),
    Difficulty(
        name = "Intermediate",
        id = "intermediate",
        color = Color(0xFF90EE90),
        icon = Icons.Default.Favorite,
        description = "Balanced mode. Suitable for average learners seeking challenge.",
        xpMultiplier = 1.0,
        getMaxLifeCount = { streak -> 3 + streak / 10 },
        getKeepEssenceOnFail = { lives -> lives > 1 },
        getLifeCost = { lives -> 35 * lives },
        getXpEarned = { performance -> (performance * 100).toInt() },
        getGemReward = { xp, livesUsed -> (xp / 200) - livesUsed },
        getFrozenStreakCost = { streak -> 50 + streak * 5 },
        getMaxFrozenStreakCount = { streak -> 2 + streak / 50 },
        typoConfidenceThreshold = 0.82
    ),
    Difficulty(
        name = "Advanced",
        id = "advanced",
        color = Color(0xFFFFD700),
        icon = Icons.Default.Warning,
        description = "Higher challenge. Mistakes are penalized, rewards are higher.",
        xpMultiplier = 1.5,
        getMaxLifeCount = { streak -> 2 + streak / 20 },
        getKeepEssenceOnFail = { _ -> false },
        getLifeCost = { lives -> 50 * lives },
        getXpEarned = { performance -> (performance * 120).toInt() },
        getGemReward = { xp, livesUsed -> (xp / 150) - (2 * livesUsed) },
        getFrozenStreakCost = { streak -> 75 + streak * 10 },
        getMaxFrozenStreakCount = { streak -> 1 + streak / 100 },
        typoConfidenceThreshold = 0.9
    ),
    Difficulty(
        name = "Expert",
        id = "expert",
        color = Color(0xFF808080),
        icon = Icons.Default.Block,
        description = "Very challenging mode. Only for experienced learners.",
        xpMultiplier = 2.0,
        getMaxLifeCount = { _ -> 1 },
        getKeepEssenceOnFail = { _ -> false },
        getLifeCost = { lives -> 75 * lives },
        getXpEarned = { performance -> (performance * 150).toInt() },
        getGemReward = { xp, livesUsed -> (xp / 100) - (3 * livesUsed) },
        getFrozenStreakCost = { streak -> 100 + streak * 25 },
        getMaxFrozenStreakCount = { _ -> 0 },
        getIsUnlocked = { exp -> exp >= 5000 },
        typoConfidenceThreshold = 0.96
    ),
    Difficulty(
        name = "Master",
        id = "master",
        color = Color(0xFF505050),
        icon = Icons.Default.Bolt,
        description = "One-chance mode. Maximum rewards but almost impossible.",
        xpMultiplier = 3.0,
        getMaxLifeCount = { _ -> 1 },
        getKeepEssenceOnFail = { _ -> false },
        getLifeCost = { _ -> Int.MAX_VALUE },
        getXpEarned = { performance -> (performance * 200).toInt() },
        getGemReward = { xp, livesUsed -> if (livesUsed == 0) xp / 50 else 0 },
        getFrozenStreakCost = { _ -> Int.MAX_VALUE },
        getMaxFrozenStreakCount = { _ -> 0 },
        getIsUnlocked = { exp -> exp >= 20000 },
        typoConfidenceThreshold = 1.0
    )
)



