package com.example.questionnaire

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {
    data class QuizViewState(
        val index: Int = 0,
        val questions: List<Question> = listOf(question1, question2, question3),
        val statelist: List<Boolean> = emptyList()
    )

    val mutableStateFlow = MutableStateFlow(QuizViewState())
    val stateFlow = mutableStateFlow.asStateFlow()

//    fun SingleChoiceClick() {
//        mutableStateFlow.update
//    }

    fun navigateToNextQuestion() {
        mutableStateFlow.update { it.copy(index = it.index + 1) }
    }

    fun navigateToPreviousQuestion() {
        mutableStateFlow.update { it.copy(index = it.index - 1) }
    }
}