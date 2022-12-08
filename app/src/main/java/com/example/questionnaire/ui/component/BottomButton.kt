package com.example.questionnaire

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText

@Composable
fun BottomButton(
    onNavigateToPrevious: () -> Unit = {},
    onNavigateToNext: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .absolutePadding(bottom = 20.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = onNavigateToPrevious,
            modifier = Modifier.weight(1f).padding(all = 10.dp)
        ) {
            CustomText(
                text = "Previous",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Button(
            onClick = onNavigateToNext,
            modifier = Modifier.weight(1f).padding(all = 10.dp)
        ) {
            CustomText(text = "Next", style = MaterialTheme.typography.titleLarge)
        }
    }
}