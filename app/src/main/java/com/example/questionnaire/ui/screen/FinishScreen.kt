package com.example.questionnaire.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.App
import com.example.questionnaire.data.QuestionnaireClass
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.serializable.Encode
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.navigate.RouteName
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlin.concurrent.thread

@Composable
@Preview
fun FinishScreen(
    quizViewModel: QuizViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val viewState by quizViewModel.stateFlow.collectAsState()
    QuestionnaireTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomText(
                    text = "Accomplished!",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 275.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        if (!viewState.show) {
                            thread {
                                App.db.questionnaireClassDao().insertAll(
                                    QuestionnaireClass(
                                        topic = viewState.topic,
                                        json = Encode(viewState.questions)
                                    )
                                )
                            }
                            navController.navigate(RouteName.HOME_SCREEN)
                            { popUpTo(RouteName.HOME_SCREEN) { inclusive = true } }
                        } else {
                            navController.navigate(RouteName.DATABASE_SCREEN)
                            { popUpTo(RouteName.DATABASE_SCREEN) { inclusive = true } }
                        }
                    },
                    modifier = Modifier
                        .padding(all = 30.dp)
                        .fillMaxWidth()
                ) {
                    CustomText(
                        text = when (viewState.show) {
                            false -> "Back To Home"
                            true -> "Back To Database"
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}