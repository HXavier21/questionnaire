package com.example.questionnaire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlin.String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportScreen(
    text: String = "",
    textChange: (String) -> Unit = {},
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {}
) {
    QuestionnaireTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                CustomText(
                    text = "Please Import Questionnaire(.json)",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 60.dp, bottom = 50.dp, start = 8.dp)
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = textChange,
                    label = { CustomText("Questionnaire(.json)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(520.dp)
                        .padding(all = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                BottomButton(onNavigateToPrevious, onNavigateToNext)
            }
        }
    }
}

@Preview
@Composable
fun ImportScreenPreview(
) {
    var text by remember { mutableStateOf("") }
    ImportScreen(text, { text = it })
}
