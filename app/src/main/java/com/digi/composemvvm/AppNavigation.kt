package com.digi.composemvvm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digi.composemvvm.ui.screens.AppViewModel
import com.digi.composemvvm.ui.screens.ArticleScreen
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
    val blogState = vm.blogState.collectAsState().value
    val loginState = vm.loginState.collectAsState().value
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route){

            if (loginState.isLoginCompleted){
                navController.navigate(Screen.Blog.route)
            }
            LoginScreen(
                modifier = modifier,
                state = loginState, // send the value of state to the UI
                onEvent = vm::onLoginEvent, // connect the event from ui to viewmodel event function, ::  scope resolution operator
            )
        }
        composable(Screen.Blog.route){
            BlogScreen(
                modifier = modifier,
                blogState = blogState,
                loginState = loginState,
                onEvent = vm::onBlogEvent,
            )
            if(blogState.selectedArticle!=null){
                navController.navigate(Screen.Article.route)
            }
        }
        composable(Screen.Article.route){
            if(blogState.selectedArticle == null){
                navController.popBackStack()
            }
            ArticleScreen(
                modifier = modifier,
                blogState = blogState,
                loginState = loginState,
                onEvent = vm::onBlogEvent,
            )

        }
    }
}