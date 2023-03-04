package com.example.questionnaire.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questionnaire.data.QuizViewModel
import com.example.questionnaire.ui.component.MyTextField
import com.example.questionnaire.ui.theme.QuestionnaireTheme

@Composable
fun ImportScreen(
    quizViewModel: QuizViewModel = viewModel(),
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {}
) {
    QuestionnaireTheme {
        Surface {

        }
    }
}

@Preview
@Composable
fun ImportScreenPreview() {
    ImportScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SingleChoiceItem(){
    QuestionnaireTheme {
        Surface {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "01",
                        style = MaterialTheme.typography.titleLarge)
                    MyTextField(label = "Input Title")
                }

            }
        }
    }
}