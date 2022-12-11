package com.example.questionnaire

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.data.QuestionnaireClass
import com.example.questionnaire.data.QuestionnaireViewModel
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.ui.QuizScreen
import com.example.questionnaire.ui.navigate.RouteName
import com.example.questionnaire.ui.screen.DatabaseItem
import com.example.questionnaire.ui.screen.DatabaseScreen
import com.example.questionnaire.ui.screen.FinishScreen
import kotlin.String
import kotlin.concurrent.thread

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteName.HOME_SCREEN
) {
    val quizViewModel: QuizViewModel = viewModel()
    val questionnaireViewModel: QuestionnaireViewModel = viewModel()
    val viewState by questionnaireViewModel.stateFlow.collectAsState()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RouteName.HOME_SCREEN) {
            HomeScreen(
                onNavigateToDatabase = {
                    questionnaireViewModel.syncquestionnaire()
                    navController.navigate(RouteName.DATABASE_SCREEN)
                },
                onNavigateToImport = {
                    quizViewModel.topicresume()
                    navController.navigate(RouteName.IMPORT_SCREEN)
                }
            )
        }
        composable(RouteName.DATABASE_SCREEN) {
            DatabaseScreen(
                questionnairelist = viewState,
                quizViewModel = quizViewModel,
                navController = navController
            )
        }
        composable(RouteName.IMPORT_SCREEN) {
            ImportScreen(
                quizViewModel = quizViewModel,
                onNavigateToPrevious = {
                    navController.navigate(RouteName.HOME_SCREEN)
                    { popUpTo(RouteName.HOME_SCREEN) { inclusive = true } }
                },
                onNavigateToNext = { navController.navigate(RouteName.QUIZ_SCREEN) }
            )
        }
        composable(RouteName.QUIZ_SCREEN) {
            QuizScreen(
                quizViewModel = quizViewModel,
                navController = navController
            )
        }
        composable(RouteName.FINISH_SCREEN) {
            FinishScreen(
                quizViewModel = quizViewModel,
                navController = navController
            )
        }
    }
}