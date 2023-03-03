package com.example.questionnaire.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun SingleChoiceItem() {
    QuestionnaireTheme {
        Surface {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "01",
                        style = MaterialTheme.typography.titleLarge
                    )
                    MyTextField(label = "Input headline")
                }

            }
        }
    }
}

//@Preview
@Composable
fun MultipleChoiceItem() {
    QuestionnaireTheme {
        Surface {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "02",
                        style = MaterialTheme.typography.titleLarge
                    )
                    MyTextField(label = "Input headline")
                }

            }
        }
    }
}

//@Preview
@Composable
fun BlankFillItem() {
    QuestionnaireTheme {
        Surface(modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.small)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "01",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    MyTextField(label = "Input headline")
                }
                MyTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 10.dp),
                    label = "User to input",
                    enabled = false,
                    disabledIndicatorColor = Color.Gray,
                    shape = MaterialTheme.shapes.small
                )
            }
        }
    }
}