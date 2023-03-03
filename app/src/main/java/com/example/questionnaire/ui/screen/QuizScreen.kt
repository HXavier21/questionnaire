package com.example.questionnaire.ui.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.BlankFill
import com.example.questionnaire.BlankFillInputBox
import com.example.questionnaire.BottomButton
import com.example.questionnaire.Headline
import com.example.questionnaire.MultipleChoice
import com.example.questionnaire.MultipleChoiceOptionItem
import com.example.questionnaire.ProgressIndicator
import com.example.questionnaire.Question
import com.example.questionnaire.R
import com.example.questionnaire.SingleChoice
import com.example.questionnaire.SingleChoiceOptionItem
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.serializable.Encode
import com.example.questionnaire.ui.navigate.RouteName
import com.example.questionnaire.ui.theme.QuestionnaireTheme

private const val TAG = "QuizScreen"

@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    viewState.run {
        QuizScreenImpl(
            show = show,
            index = index + 1,
            total = questions.size,
            question = questions[index],
            onNavigateToNext = {
                if (index < questions.size - 1)
                    quizViewModel.navigateToNextQuestion()
                else {
                    if (!show) {
                        for (quetion in questions) {
                            when (quetion) {
                                is MultipleChoice -> {
                                    quetion.checkedItems.addAll(quetion.checked.toList())
                                }

                                is SingleChoice -> {
                                    quetion.selectedItem = quetion.selected.value
                                }

                                is BlankFill -> {
                                    quetion.answer = quetion.text.value
                                }
                            }
                        }
                        Log.d(TAG, "checkeditem: " + Encode(questions))
                    }
                    navController.navigate(RouteName.FINISH_SCREEN)
                }
            },
            onNavigateToPrevious = {
                if (index > 0)
                    quizViewModel.navigateToPreviousQuestion()
                else {
                    if (!show)
                        navController.navigate(RouteName.IMPORT_SCREEN)
                    else
                        navController.navigate(RouteName.DATABASE_SCREEN)
                }
            }
        )
    }
}

@Composable
fun QuizScreenImpl(
    show: Boolean = false,
    index: Int = 1,
    total: Int = 3,
    question: Question,
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {},
) {
    BackHandler(index != 0) {
        onNavigateToPrevious()
    }
    QuestionnaireTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxHeight()) {
                ProgressIndicator(section = index, total = total)
                Headline(
                    type = when (question) {
                        is BlankFill -> stringResource(R.string.blank_fill)
                        is MultipleChoice -> stringResource(R.string.multiple_choice)
                        is SingleChoice -> stringResource(R.string.single_choice)
                    }, headline = question.headline
                )
                when (question) {
                    is BlankFill -> {
                        BlankFillInputBox(
                            modifier = Modifier
                                .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)
                                .fillMaxWidth()
                                .weight(1f),
                            text = when (show) {
                                false -> question.text.value
                                true -> question.answer
                            },
                            onValueChange = {
                                if (!show)
                                    question.text.value = it
                            }
                        )
                        Log.d(TAG, "answer:" + question.answer)
                    }

                    is MultipleChoice -> {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                        ) {
                            itemsIndexed(question.options) { index, option ->
                                MultipleChoiceOptionItem(
                                    text = option,
                                    onCheckedChange = {
                                        if (!show)
                                            if (it) question.checked.add(index)
                                            else question.checked.remove(index)
                                    },
                                    checked = when (show) {
                                        false -> question.checked.contains(index)
                                        true -> question.checkedItems.contains(index)
                                    },
                                )
                            }
                        }
                    }

                    is SingleChoice -> {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                        ) {
                            itemsIndexed(question.options) { index, option ->
                                SingleChoiceOptionItem(
                                    text = option,
                                    onClick = {
                                        if (!show)
                                            question.selected.value = index
                                    },
                                    selected = when (show) {
                                        false -> (question.selected.value == index)
                                        true -> (question.selectedItem == index)
                                    },
                                    index = index
                                )

                            }
                        }
                    }
                }
                BottomButton(onNavigateToPrevious, onNavigateToNext)
            }
        }
    }
}