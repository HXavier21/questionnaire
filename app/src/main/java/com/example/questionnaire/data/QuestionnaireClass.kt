package com.example.questionnaire.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionnaireClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val topic: String = "",
    @ColumnInfo val json: String = ""
)