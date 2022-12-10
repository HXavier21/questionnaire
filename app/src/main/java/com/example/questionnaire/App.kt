package com.example.questionnaire

import android.app.Application
import androidx.room.Room
import com.example.questionnaire.data.QuestionnaireDatabase

class App :Application(){
    companion object{
        lateinit var db:QuestionnaireDatabase
    }

    override fun onCreate() {
        db = Room.databaseBuilder(
            applicationContext,
            QuestionnaireDatabase::class.java,
            "QuestionnaireDatabase"
        ).build()
        super.onCreate()
    }
}