package com.example.questionnaire.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.R
import com.example.questionnaire.data.QuizViewModel
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
                    text = stringResource(R.string.accomplished),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 275.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        if (!viewState.show) {
                            thread {
                                //App.db.userClassDao().insertAll()
                            }
                            navController.navigate(RouteName.HOME_SCREEN)
                            { popUpTo(RouteName.HOME_SCREEN) { inclusive = true } }
                        } else {
                            navController.navigate(RouteName.DATABASE_SCREEN)
                            { popUpTo(RouteName.DATABASE_SCREEN) { inclusive = true } }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 30.dp)
                ) {
                    CustomText(
                        text = when (viewState.show) {
                            false -> stringResource(R.string.back_to_home)
                            true -> stringResource(R.string.back_to_database)
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}