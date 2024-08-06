package com.digi.composemvvm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


sealed class LoginEvent {
    data class OnUsernameChange(val name: String) : LoginEvent()
    data class OnPasswordChange(val pwd: String) : LoginEvent()
    data object OnLoginClick : LoginEvent()
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit = {},
) {


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.colorScheme.primary,
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .width(300.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large,
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Login ",
                style = MaterialTheme.typography.headlineSmall
            )
            HorizontalDivider()
            OutlinedTextField(
                value = state.username,
                label = { Text(text = "Username") },
                onValueChange = {
                    onEvent(LoginEvent.OnUsernameChange(it))
                },
                isError = state.isError
            )
            OutlinedTextField(
                value = state.password,
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    onEvent(LoginEvent.OnPasswordChange(it))
                },
                isError = state.isError
            )
            ExtendedFloatingActionButton(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    onEvent(LoginEvent.OnLoginClick)
                }) {
                Icon(imageVector = Icons.Default.PersonOutline, contentDescription = "Login")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Login to Account")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        state = LoginState(username = "Priyash", password = "12234")
    )
}