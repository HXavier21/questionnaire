package com.example.questionnaire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(onNavigateToCreate = {}, onNavigateToImport = {})

        }
    }
}

data class Message(val author: String, val body: String)

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
                onNavigateToCreate = { navController.navigate("CreateScreen") },
                onNavigateToImport = { navController.navigate("ImportScreen") }
            )
        }
        composable("CreateScreen") { CreateScreen(/*...*/) }
        composable("ImportScreen") {}
    }
}

@Composable
fun HomeScreen(onNavigateToCreate: () -> Unit, onNavigateToImport: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {}
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToImport
                ) {
                    Text(
                        text = "Import Questionnaire",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = "or",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToCreate
                ) {
                    Text(
                        text = "Create New Questionnaire",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CreateScreen(){
    Surface(){

    }
}

@Preview
@Composable
fun PreviewHome() {
    HomeScreen(onNavigateToCreate = {}, onNavigateToImport = {})
}