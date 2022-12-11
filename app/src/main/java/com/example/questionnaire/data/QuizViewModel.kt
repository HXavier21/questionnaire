package com.example.questionnaire.data

import androidx.lifecycle.ViewModel
import com.example.questionnaire.App
import com.example.questionnaire.Question
import com.example.questionnaire.obj
import com.example.questionnaire.serializable.Encode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.concurrent.thread

class QuizViewModel : ViewModel() {
    data class QuizViewState(
        val show: Boolean = false,
        val index: Int = 0,
        val questions: List<Question> = emptyList(),
        val topic: String = ""
    )

    val mutableJsonContentFlow = MutableStateFlow(Encode(obj))

    val mutableStateFlow = MutableStateFlow(QuizViewState())
    val stateFlow = mutableStateFlow.asStateFlow()

    fun topicresume(){
        mutableStateFlow.update { it.copy(topic = "") }
    }

    fun navigateToNextQuestion() {
        mutableStateFlow.update { it.copy(index = it.index + 1) }
    }

    fun navigateToPreviousQuestion() {
        mutableStateFlow.update { it.copy(index = it.index - 1) }
    }
}

class QuestionnaireViewModel : ViewModel() {
    val quetionnairelist: List<QuestionnaireClass> = mutableListOf()

    val mutableStateFlow = MutableStateFlow(quetionnairelist)
    val stateFlow = mutableStateFlow.asStateFlow()

    fun syncquestionnaire() {
        thread {
            mutableStateFlow.update { App.db.questionnaireClassDao().getAll() }
        }
    }

}