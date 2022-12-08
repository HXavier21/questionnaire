package com.example.questionnaire.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme


@Composable
@Preview
fun FinishScreen(onClick: () -> Unit = {}) {
    QuestionnaireTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomText(
                    text = "Accomplished!",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 275.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .padding(all = 30.dp)
                        .fillMaxWidth()
                ) {
                    CustomText(
                        text = "Back to Home",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}