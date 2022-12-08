package com.example.questionnaire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import kotlin.String

@Composable
fun HeadingScreen(
    type: QuestionType = QuestionType.Single_Choice,
    headline: String = ""
) {
    Column() {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier
                .padding(start = 5.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            CustomText(
                text = type.toString(),
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomText(
            text = headline,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(start = 8.dp),
            fontWeight = FontWeight.Normal
        )
    }
}