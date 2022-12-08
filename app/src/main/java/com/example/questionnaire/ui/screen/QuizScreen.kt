package com.example.questionnaire.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.questionnaire.BlankFillInputBox
import com.example.questionnaire.BottomButton
import com.example.questionnaire.HeadingScreen
import com.example.questionnaire.MultipleChoiceOptionItem
import com.example.questionnaire.ProgressIndicator
import com.example.questionnaire.QuestionType
import com.example.questionnaire.QuizViewModel
import com.example.questionnaire.SingleChoiceOptionItem
import com.example.questionnaire.total
import com.example.questionnaire.ui.theme.QuestionnaireTheme

@Composable
fun QuizScreen(
    quizViewModel: QuizViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    viewState.run {
        if (index + 1 == total)
            QuizScreenImpl(
                index = index + 1,
                type = questions[index].type,
                headline = questions[index].headline,
                options = questions[index].options,
                onClick = {},
                onNavigateToNext = { navController.navigate("FinishScreen") },
                onNavigateToPrevious = { quizViewModel.navigateToPreviousQuestion() }
            )
        else if (index == 0)
            QuizScreenImpl(
                index = index + 1,
                type = questions[index].type,
                headline = questions[index].headline,
                options = questions[index].options,
                onClick = {},
                onNavigateToNext = { quizViewModel.navigateToNextQuestion() },
                onNavigateToPrevious = { navController.navigate("ImportScreen") }
            )
        else
            QuizScreenImpl(
                index = index + 1,
                type = questions[index].type,
                headline = questions[index].headline,
                options = questions[index].options,
                onClick = {},
                onNavigateToNext = { quizViewModel.navigateToNextQuestion() },
                onNavigateToPrevious = { quizViewModel.navigateToPreviousQuestion() }
            )
    }
}

@Composable
fun QuizScreenImpl(
    index: Int = 1,
    total: Int = 3,
    type: QuestionType = QuestionType.Single_Choice,
    headline: String = "",
    options: List<String> = listOf(),
    text: String = "",
    onClick: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    onValueChanged: (String) -> Unit = {},
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {}
) {
    QuestionnaireTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Spacer(modifier = Modifier.height(10.dp))
                ProgressIndicator(section = index, total = total)
                Spacer(modifier = Modifier.height(40.dp))
                HeadingScreen(type = type, headline = headline)
                Spacer(modifier = Modifier.height(20.dp))
                if (type != QuestionType.Blank_Fill) {
                    LazyColumn(
                        modifier = Modifier.requiredHeight(450.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (type == QuestionType.Single_Choice)
                            for (option in options) item {
                                SingleChoiceOptionItem(
                                    text = option,
//                                    selected =,
                                    onClick = onClick
                                )
                            }
                        else
                            for (option in options) item {
                                MultipleChoiceOptionItem(
                                    text = option,
//                                    checked =,
                                    onCheckedChange = onCheckedChange
                                )
                            }
                    }
                } else {
                    BlankFillInputBox(
                        text = text,
                        onValueChange = onValueChanged
                    )
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