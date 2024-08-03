package com.digi.composemvvm

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Screen(val route: String) {
    Login("login"),
    Blog("blog"),
    Article("article"),
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    // todo create viewmodel object
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route){
//            LoginScreen(
//                modifier = modifier,
//                state =,
//                onEvent = ,
//            )
        }
        composable(Screen.Blog.route){
//            BlogScreen(
//                modifier = modifier,
//                state =,
//                onEvent = ,
//            )
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