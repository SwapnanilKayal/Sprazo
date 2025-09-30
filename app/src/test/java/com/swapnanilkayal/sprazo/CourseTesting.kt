package com.swapnanilkayal.sprazo

import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Lessons
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Units
import com.swapnanilkayal.sprazo.Data.Course.QuestionBank
import org.junit.Assert.assertEquals
import org.junit.Test

class CourseTesting {
    @Test
    fun UniqueIDs(){
        val ids = mutableListOf<String>()
        val dualIds = mutableListOf<String>()
        Lessons.forEach {
            println(it.id)
            if (ids.contains(it.id)) dualIds.add(it.id)
            ids.add(it.id)
        }
        Units.forEach {
            println(it.id)
            if (ids.contains(it.id)) dualIds.add(it.id)
            ids.add(it.id)
        }
        QuestionBank.questions.forEach {
            println(it.id)
            if (ids.contains(it.id)) dualIds.add(it.id)
            ids.add(it.id)
        }
        Sections.forEach {
            println(it.id)
            if (ids.contains(it.id)) dualIds.add(it.id)
            ids.add(it.id)
        }
        println(dualIds)
        assertEquals(0,dualIds.size)
    }
}