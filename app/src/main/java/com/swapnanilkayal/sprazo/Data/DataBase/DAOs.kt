package com.swapnanilkayal.sprazo.Data.DataBase














/*
@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<QuestionEntity>)

    @Query("SELECT * FROM questions WHERE id = :id LIMIT 1")
    suspend fun getQuestionById(id: String): QuestionEntity?

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE focusTopic = :focusTopic LIMIT :limit")
    suspend fun getQuestionsByFocus(focusTopic: String, limit: Int): List<QuestionEntity>

    @Delete
    suspend fun delete(question: QuestionEntity)

    @Query("DELETE FROM questions")
    suspend fun clearAll()
}

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: LessonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lessons: List<LessonEntity>)

    @Query("SELECT * FROM lessons WHERE id = :id LIMIT 1")
    suspend fun getLessonById(id: String): LessonEntity?

    @Query("SELECT * FROM lessons")
    suspend fun getAllLessons(): List<LessonEntity>

    @Delete
    suspend fun delete(lesson: LessonEntity)

    @Query("DELETE FROM lessons")
    suspend fun clearAll()
}

@Dao
interface UnitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(unit: UnitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(units: List<UnitEntity>)

    @Query("SELECT * FROM units WHERE id = :id LIMIT 1")
    suspend fun getUnitById(id: String): UnitEntity?

    @Query("SELECT * FROM units")
    suspend fun getAllUnits(): List<UnitEntity>

    @Delete
    suspend fun delete(unit: UnitEntity)

    @Query("DELETE FROM units")
    suspend fun clearAll()
}

@Dao
interface SectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(section: SectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sections: List<SectionEntity>)

    @Query("SELECT * FROM sections WHERE id = :id LIMIT 1")
    suspend fun getSectionById(id: String): SectionEntity?

    @Query("SELECT * FROM sections")
    suspend fun getAllSections(): List<SectionEntity>

    @Delete
    suspend fun delete(section: SectionEntity)

    @Query("DELETE FROM sections")
    suspend fun clearAll()
}


*/