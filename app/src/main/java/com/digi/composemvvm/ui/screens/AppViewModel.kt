package com.digi.composemvvm.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoginCompleted: Boolean = false,
    val isError: Boolean = false,
)

// rules of MVVM architecture
// 1. Viewmodel can update the State and send the info to the UI
// 2. UI can send the info to the ViewModel using an Event
// 3. ViewModel will have to object of same state 1. Mutable 2. Immutable
// 4. mutable state object's name start with _ (underscore)
// 5. An event can send data from UI to ViewModel for updating the state

class AppViewModel() : ViewModel() {
    // state object
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    // event logic
    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLoginClick -> {
                checkLogin()
            }

            is LoginEvent.OnPasswordChange -> {
                _loginState.update { it.copy(password = event.pwd) }
            }

            is LoginEvent.OnUsernameChange -> {
                _loginState.update { it.copy(username = event.name) }
            }
        }
    }

    private fun checkLogin() {

    }
}