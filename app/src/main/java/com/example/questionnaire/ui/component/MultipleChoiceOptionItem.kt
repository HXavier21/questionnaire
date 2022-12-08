package com.example.questionnaire

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme
import kotlin.String

@Composable
fun MultipleChoiceOptionItem(
    text: String = "",
    checked: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    val animatedDp by animateDpAsState(targetValue = if (checked) 5.dp else 0.dp, tween(100))
    val animatedOutlineColor by animateColorAsState(
        targetValue = if (checked) MaterialTheme.colorScheme.primary else
            MaterialTheme.colorScheme.secondaryContainer, tween(100)
    )
    Surface(shape = shape, tonalElevation = animatedDp, color = MaterialTheme.colorScheme.surface) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.5f.dp,
                    color = animatedOutlineColor,
                    shape = shape
                )
                .clickable { onCheckedChange(!checked) }
        ) {
            CustomText(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .weight(1f)
            )
            Checkbox(
                checked = checked,
                onCheckedChange = null,
                modifier = Modifier.padding(all = 20.dp)
            )
        }
    }
}
