package com.swapnanilkayal.sprazo.Data

data class Progress(var quizeProgressPool: List<quizProgress>, var progress: Float, var progressLevel:String)
data class quizProgress(var quizId: String, var score:Int, var done: Boolean)


fun getProgress(): Progress {
    return Progress(emptyList(), 0.0f, "N/A")
}