package com.example.questionnaire.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val account:String = "",
    @ColumnInfo val password:String = "",
    @ColumnInfo val questionnaire: List<Questionnaire> = listOf()
){
    fun update(){
        questionnaire.toMutableList()
    }
}

data class Questionnaire(
    val topic: String = "",
    val json: String = ""
)
