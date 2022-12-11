package com.example.questionnaire

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.serializable.Decode
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {}
) {
    val text by quizViewModel.mutableJsonContentFlow.collectAsState()
    val viewState by quizViewModel.stateFlow.collectAsState()
    BackHandler {
        onNavigateToPrevious()
    }
    QuestionnaireTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                CustomText(
                    text = "Please Import Questionnaire(topic&.json)",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 60.dp, bottom = 50.dp, start = 8.dp)
                )
                OutlinedTextField(
                    value = viewState.topic,
                    onValueChange = { t -> quizViewModel.mutableStateFlow.update { it.copy(show = false,topic = t) } },
                    label = { CustomText(text = "Topic") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp)
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = { s -> quizViewModel.mutableJsonContentFlow.update { s } },
                    label = { CustomText("Questionnaire(.json)") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 10.dp)
                )
                BottomButton(onNavigateToPrevious) {
                    quizViewModel.mutableStateFlow.update { it.copy(index = 0,show = false,questions = Decode(text)) }
                    onNavigateToNext()
                }
            }
        }
    }
}

@Preview
@Composable
fun ImportScreenPreview(
) {
    ImportScreen()
}
