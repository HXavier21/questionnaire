package com.example.questionnaire.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionnaireClassDao {
    @Query("SELECT * FROM userClass")
    fun getAll():List<UserClass>

    @Delete
    fun delete(userClass: UserClass)

    @Insert
    fun insertAll(vararg userClasses: UserClass)
}