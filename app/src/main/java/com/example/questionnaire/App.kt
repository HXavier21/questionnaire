package com.example.questionnaire

import android.app.Application
import androidx.room.Room
import com.example.questionnaire.data.UserClassDatabase

class App :Application(){
    companion object{
        lateinit var db:UserClassDatabase
    }

    override fun onCreate() {
        db = Room.databaseBuilder(
            applicationContext,
            UserClassDatabase::class.java,
            "QuestionnaireDatabase"
        ).build()
        super.onCreate()
    }
}