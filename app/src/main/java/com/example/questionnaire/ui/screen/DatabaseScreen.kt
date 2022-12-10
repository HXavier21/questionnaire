package com.example.questionnaire.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.questionnaire.R
import com.example.questionnaire.data.QuestionnaireClass
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.serializable.Decode
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlinx.coroutines.flow.update

private const val TAG = "DatabaseScreen"

@Composable
fun DatabaseItem(
    topic: String = "",
    json: String = "",
    navController: NavController = rememberNavController(),
    quizViewModel: QuizViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5f.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                quizViewModel.mutableStateFlow.update { it.copy(questions = Decode(json)) }
                navController.navigate("QuizScreen")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            text = topic,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(20.dp)
                .weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.director),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(20.dp)
        )
    }
}

@Composable
fun DatabaseScreen(
    questionnairelist: List<QuestionnaireClass>,
    quizViewModel: QuizViewModel = viewModel()
) {
    QuestionnaireTheme {
        Column() {
            CustomText(
                text = "Questionnaire Database",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(all = 20.dp)
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                for (questionnaire in questionnairelist) {
                    item {
                        DatabaseItem(
                            topic = questionnaire.topic,
                            json = questionnaire.json,
                            quizViewModel = quizViewModel
                        )
                    }
                    Log.d(TAG, "topic:"+questionnaire.topic+"\njson:"+questionnaire.json)
                }
            }
        }
    }
}