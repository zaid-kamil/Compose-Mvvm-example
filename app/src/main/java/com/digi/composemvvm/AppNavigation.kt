package com.digi.composemvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digi.composemvvm.ui.screens.AppViewModel
import com.digi.composemvvm.ui.screens.BlogScreen
import com.digi.composemvvm.ui.screens.LoginScreen

enum class Screen(val route: String) {
    Login("login"),
    Blog("blog"),
    Article("article"),
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val vm: AppViewModel = viewModel() // this is not a constructor. its a function to get the instance of the viewmodel
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route){
            val currentState = vm.loginState.collectAsState().value
            if (currentState.isLoginCompleted){
                navController.navigate(Screen.Blog.route)
            }
            LoginScreen(
                modifier = modifier,
                state = currentState, // send the value of state to the UI
                onEvent = vm::onLoginEvent, // connect the event from ui to viewmodel event function, ::  scope resolution operator
            )
        }
        composable(Screen.Blog.route){
            val blogState = vm.blogState.collectAsState().value
            BlogScreen(
                modifier = modifier,
                blogState = blogState,
                loginState = vm.loginState.collectAsState().value,
                onEvent = {},
            )
        }
        composable(Screen.Article.route){
//            ArticleScreen(
//                modifier = modifier,
//                state =,
//                onEvent = ,
//            )
        }
    }
}