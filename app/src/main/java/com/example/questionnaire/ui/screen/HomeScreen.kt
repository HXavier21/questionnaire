package com.example.questionnaire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.CustomText
import com.example.questionnaire.ui.theme.QuestionnaireTheme

@Composable
fun HomeScreen(onNavigateToDatabase: () -> Unit, onNavigateToImport: () -> Unit) {
    QuestionnaireTheme() {
        Surface() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)
                            .weight(12f),
                        onClick = onNavigateToImport
                    ) {
                        CustomText(
                            text = stringResource(R.string.import_questionnaire),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                CustomText(
                    text = stringResource(R.string.or),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 30.dp,
                                start = 10.dp,
                                end = 10.dp,
                                top = 10.dp
                            )
                            .weight(12f),
                        onClick = onNavigateToDatabase
                    ) {
                        CustomText(
                            text = stringResource(R.string.questionnaires_database),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(onNavigateToDatabase = { /*TODO*/ }) {
    }
}