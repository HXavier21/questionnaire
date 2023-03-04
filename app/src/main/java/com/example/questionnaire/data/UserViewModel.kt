package com.example.questionnaire.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.concurrent.thread


class UserViewModel : ViewModel() {
    val quetionnairelist: List<User> = mutableListOf()

    val mutableStateFlow = MutableStateFlow(quetionnairelist)
    val stateFlow = mutableStateFlow.asStateFlow()

    fun syncquestionnaire() {
        thread {
            //mutableStateFlow.update { App.db.userClassDao().getAll() }
        }
    }

}