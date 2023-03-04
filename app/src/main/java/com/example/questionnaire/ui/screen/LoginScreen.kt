package com.example.questionnaire.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questionnaire.ui.component.MyTextField
import com.example.questionnaire.ui.theme.QuestionnaireTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    loginEffect: () -> Unit = {},
    registerEffect: () -> Unit = {}
) {
    var account by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val (focusRequester) = FocusRequester.createRefs()
    QuestionnaireTheme {
        Surface {
            Column {
                Spacer(modifier = Modifier.weight(1f))
                Column(Modifier.weight(1f)) {
                    MyTextField(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .border(
                                color = Color.Black,
                                width = 0.5.dp,
                                shape = MaterialTheme.shapes.extraLarge
                            )
                            .fillMaxWidth()
                            .onKeyEvent {
                                if (it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                                    focusRequester.requestFocus()
                                }
                                false
                            },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusRequester.requestFocus() }
                        ),
                        label = "Account",
                        value = account,
                        onValueChange = { account = it }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    MyTextField(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .border(
                                color = Color.Black,
                                width = 0.5.dp,
                                shape = MaterialTheme.shapes.extraLarge
                            )
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        label = "Password",
                        value = password,
                        onValueChange = { password = it }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Button(
                            onClick = loginEffect,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 20.dp, end = 5.dp)
                        ) {
                            Text(text = "Login", style = MaterialTheme.typography.titleLarge)
                        }
                        Button(
                            onClick = registerEffect,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp, end = 20.dp)
                                .border(
                                    width = 0.5.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.extraLarge
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                text = "Register",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}