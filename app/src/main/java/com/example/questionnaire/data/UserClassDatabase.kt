package com.example.questionnaire.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Questionnaire::class], version = 1, exportSchema = false)
abstract class UserClassDatabase:RoomDatabase() {
    abstract fun userClassDao(): UserClassDao
}