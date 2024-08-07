package com.digi.composemvvm.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import com.digi.composemvvm.models.Article
import com.digi.composemvvm.models.getArticle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// rules of MVVM architecture
// 1. Viewmodel can update the State and send the info to the UI
// 2. UI can send the info to the ViewModel using an Event
// 3. ViewModel will have to object of same state 1. Mutable 2. Immutable
// 4. mutable state object's name start with _ (underscore)
// 5. An event can send data from UI to ViewModel for updating the state

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoginCompleted: Boolean = false,
    val isError: Boolean = false,
)

data class BlogState(
    val articleList: List<Article> = getArticle(),
    val selectedArticle: Article? = null,
)

class AppViewModel() : ViewModel() {
    // state object
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _blogState = MutableStateFlow((BlogState()))
    val blogState = _blogState.asStateFlow()

    // event logic for blog-screen
    fun onBlogEvent(event: BlogEvent) {
        when (event) {
            is BlogEvent.OnArticleClick -> {
                _blogState.update {
                    Log.d("Viewmodel", event.article.toString())
                    it.copy(selectedArticle = event.article)
                }
            }
        }
    }

    // event logic for loginscree
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

            LoginEvent.OnLogoutClick -> {
                _loginState.update{
                    it.copy(isLoginCompleted = false)
                }
            }
        }
    }

    private fun checkLogin() {
        val state = _loginState.value
        val authData = mapOf(
            "admin" to "admin",
            "user" to "12345",
            "tester" to "test"
        )
        authData.forEach { (un, pw) ->
            if (state.username == un && state.password == pw) {
                _loginState.update {
                    it.copy(
                        isLoginCompleted = true,
                        isError = false
                    )
                }
                return
            }
        }
        _loginState.update { it.copy(isError = true) }
    }
}
