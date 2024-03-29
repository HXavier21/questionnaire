package com.example.questionnaire.data

import androidx.lifecycle.ViewModel
import com.example.questionnaire.Question
import com.example.questionnaire.obj
import com.example.questionnaire.serializable.Encode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    data class QuizViewState(
        val show: Boolean = false,
        val index: Int = 0,
        val questions: List<Question> = emptyList(),
        val topic: String = "Fruit Preference"
    )

    var mutableJsonContentFlow = MutableStateFlow(Encode(obj))

    val mutableStateFlow = MutableStateFlow(QuizViewState())
    val stateFlow = mutableStateFlow.asStateFlow()

    fun topicResume(){
        mutableStateFlow.update { it.copy(topic = "Fruit Preference") }
    }

    fun navigateToNextQuestion() {
        mutableStateFlow.update { it.copy(index = it.index + 1) }
    }

    fun navigateToPreviousQuestion() {
        mutableStateFlow.update { it.copy(index = it.index - 1) }
    }
}