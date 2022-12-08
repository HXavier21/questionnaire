package com.example.questionnaire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlin.String

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlankFillInputBox(text: String = "", onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = { CustomText("Answer") },
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(450.dp)
    )
}

@Preview
@Composable
fun Fillblankpreview() {
    QuestionnaireTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Spacer(modifier = Modifier.height(10.dp))
                ProgressIndicator(section = 1, total = total)
                Spacer(modifier = Modifier.height(40.dp))
                HeadingScreen(type = question3.type, headline = question3.headline)
                Spacer(modifier = Modifier.height(20.dp))
                var text by rememberSaveable { mutableStateOf("") }
                BlankFillInputBox(text) { text = it }
                Spacer(modifier = Modifier.weight(1f))
                BottomButton()
            }
        }
    }
}