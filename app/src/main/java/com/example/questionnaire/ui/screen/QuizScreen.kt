package com.example.questionnaire.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.BlankFill
import com.example.questionnaire.BlankFillInputBox
import com.example.questionnaire.BottomButton
import com.example.questionnaire.HeadingScreen
import com.example.questionnaire.MultipleChoiceOptionItem
import com.example.questionnaire.MultipleChoice
import com.example.questionnaire.ProgressIndicator
import com.example.questionnaire.Question
import com.example.questionnaire.QuizViewModel
import com.example.questionnaire.SingleChoice
import com.example.questionnaire.SingleChoiceOptionItem
import com.example.questionnaire.ui.theme.QuestionnaireTheme

@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    viewState.run {

        QuizScreenImpl(
            index = index + 1,
            total = questions.size,
            question = questions[index],
            onNavigateToNext = {
                if (index < questions.size - 1)
                    quizViewModel.navigateToNextQuestion()
                else
                    navController.navigate(RouteName.FINISH_SCREEN)
            },
            onNavigateToPrevious = {
                if (index > 0)
                    quizViewModel.navigateToPreviousQuestion()
                else
                    navController.navigate(RouteName.IMPORT_SCREEN)
            }
        )
    }
}

@Composable
fun QuizScreenImpl(
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
                Spacer(modifier = Modifier.height(10.dp))
                ProgressIndicator(section = index, total = total)
                Spacer(modifier = Modifier.height(40.dp))
                HeadingScreen(
                    type = when (question) {
                        is BlankFill -> "Blank_Fill"
                        is MultipleChoice -> "Multiple_Choice"
                        is SingleChoice -> "Single_Choice"
                    }, headline = question.headline
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    modifier = Modifier.requiredHeight(450.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (question) {
                        is BlankFill -> {
                            item {
                                BlankFillInputBox(
                                    text = question.text.value,
                                    onValueChange = {
                                        question.text.value = it
                                    }
                                )
                            }
                        }

                        is MultipleChoice -> {
                            itemsIndexed(question.options) { index, option ->
                                MultipleChoiceOptionItem(
                                    text = option,
                                    onCheckedChange = {
                                        if (it) question.checked.add(index)
                                        else question.checked.remove(index)
                                    },
                                    checked = question.checked.contains(index),
                                )
                            }
                        }

                        is SingleChoice -> {
                            itemsIndexed(question.options) { index, option ->
                                SingleChoiceOptionItem(
                                    text = option,
                                    onClick = {
                                        question.selected.value = index
                                    },
                                    selected = (question.selected.value == index)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                BottomButton(onNavigateToPrevious, onNavigateToNext)
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    QuizScreen()
}