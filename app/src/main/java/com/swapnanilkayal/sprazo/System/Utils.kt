package com.swapnanilkayal.sprazo.System



fun getSkillLevelName(level:Int): String{
    return when(level){
        0 -> "beginner"
        1 -> "intermediate"
        3 -> "advanced"
        else -> "extreme"
    }
}


