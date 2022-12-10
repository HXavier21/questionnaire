package com.example.questionnaire

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.data.QuestionnaireClass
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
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RouteName.HOME_SCREEN) {
            HomeScreen(
                onNavigateToDatabase = { navController.navigate(RouteName.DATABASE_SCREEN) },
                onNavigateToImport = { navController.navigate(RouteName.IMPORT_SCREEN) }
            )
        }
        composable(RouteName.DATABASE_SCREEN) {
            var questionnairelist: MutableList<QuestionnaireClass> = mutableListOf()
            thread {
                questionnairelist.addAll(App.db.questionnaireClassDao().getAll())
            }
            DatabaseScreen(
                questionnairelist = questionnairelist,
                quizViewModel = quizViewModel
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