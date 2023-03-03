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
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.data.UserViewModel
import com.example.questionnaire.serializable.Encode
import com.example.questionnaire.ui.screen.QuizScreen
import com.example.questionnaire.ui.navigate.RouteName
import com.example.questionnaire.ui.screen.DatabaseScreen
import com.example.questionnaire.ui.screen.FinishScreen
import com.example.questionnaire.ui.screen.ImportScreen
import com.example.questionnaire.ui.screen.LoginScreen
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RouteName.HOME_SCREEN
) {
    val quizViewModel: QuizViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val viewState by userViewModel.stateFlow.collectAsState()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RouteName.LOGIN_SCREEN){
            LoginScreen(

            )
        }
        composable(RouteName.HOME_SCREEN) {
            HomeScreen(
                onNavigateToDatabase = {
                    userViewModel.syncquestionnaire()
                    navController.navigate(RouteName.DATABASE_SCREEN)
                },
                onNavigateToImport = {
                    quizViewModel.topicResume()
                    quizViewModel.mutableJsonContentFlow = MutableStateFlow(Encode(obj))
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
                quizViewModel = quizViewModel,
                navController = navController
            )
        }
    }
}