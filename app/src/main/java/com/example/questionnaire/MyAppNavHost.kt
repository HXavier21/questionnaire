package com.example.questionnaire

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.serializable.Decode
import com.example.questionnaire.serializable.Encode
import com.example.questionnaire.ui.QuizScreen
import com.example.questionnaire.ui.RouteName
import com.example.questionnaire.ui.screen.FinishScreen
import kotlin.String

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
            (TODO())
        }
        composable(RouteName.IMPORT_SCREEN) {
            ImportScreen(
                quizViewModel = quizViewModel,
                onNavigateToPrevious = { navController.navigate(RouteName.HOME_SCREEN) },
                onNavigateToNext = {
                    navController.navigate(RouteName.QUIZ_SCREEN)
                }
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
                onClick = { navController.navigate(RouteName.HOME_SCREEN) }
            )
        }
    }
}