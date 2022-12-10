package com.example.questionnaire.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionnaireClassDao {
    @Query("SELECT * FROM questionnaireclass")
    fun getAll():List<QuestionnaireClass>

    @Delete
    fun delete(questionnaireClass: QuestionnaireClass)

    @Insert
    fun insertAll(vararg questionnaireClass: QuestionnaireClass)
}