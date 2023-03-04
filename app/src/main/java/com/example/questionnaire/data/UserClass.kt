package com.example.questionnaire.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    val account: String,
    val password: String
)

@Entity
data class Questionnaire(
    @PrimaryKey(autoGenerate = true) val questionnaireId: Int,
    val userCreatorId: Int,
    val topic: String,
    val json: String
)

data class UserWithQuestionnaires(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val questionnaires: List<Questionnaire>

)