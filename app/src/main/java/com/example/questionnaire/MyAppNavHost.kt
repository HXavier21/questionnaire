package com.example.questionnaire

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.ui.QuizScreen
import com.example.questionnaire.ui.screen.FinishScreen
import kotlin.String

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "HomeScreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("HomeScreen") {
            HomeScreen(
                onNavigateToDatabase = { navController.navigate("DatabaseScreen") },
                onNavigateToImport = { navController.navigate("ImportScreen") }
            )
        }
        composable("DatabaseScreen") {
            (TODO())
        }
        composable("ImportScreen") {
            ImportScreen(
                onNavigateToPrevious = {navController.navigate("HomeScreen")},
                onNavigateToNext = {navController.navigate("QuizScreen")}
            )
        }
        composable("QuizScreen"){
            QuizScreen(
                navController = navController
            )
        }
        composable("FinishScreen"){
            FinishScreen(
                onClick = {navController.navigate("HomeScreen")}
            )
        }
    }
}