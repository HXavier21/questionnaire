package com.example.questionnaire.data

import androidx.lifecycle.ViewModel
import com.example.questionnaire.App
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.concurrent.thread


class QuestionnaireViewModel : ViewModel() {
    val quetionnairelist: List<UserClass> = mutableListOf()

    val mutableStateFlow = MutableStateFlow(quetionnairelist)
    val stateFlow = mutableStateFlow.asStateFlow()

    fun syncquestionnaire() {
        thread {
            mutableStateFlow.update { App.db.questionnaireClassDao().getAll() }
        }
    }

}