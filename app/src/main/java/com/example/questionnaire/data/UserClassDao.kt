package com.example.questionnaire.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserClassDao {
    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertQuestionnaires(vararg questionnaires: Questionnaire)

    @Query("select account from user")
    fun getAccounts(): List<String>

    @Query("select userId from user where account = :account ")
    fun getUserId(account: String): Int

    @Query("select * from questionnaire where userCreatorId = :userId")
    fun getQuestionnaires(userId: Int): List<Questionnaire>

    @Transaction
    @Query("SELECT * FROM user")
    fun getUsersWithQuestionnaires(): List<UserWithQuestionnaires>
}