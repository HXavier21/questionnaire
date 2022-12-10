package com.example.questionnaire.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionnaireClass::class], version = 1, exportSchema = false)
abstract class QuestionnaireDatabase:RoomDatabase() {
    abstract fun questionnaireClassDao(): QuestionnaireClassDao
}