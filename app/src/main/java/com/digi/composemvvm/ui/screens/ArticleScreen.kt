package com.digi.composemvvm.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.digi.composemvvm.models.getArticle

@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    blogState: BlogState,
    loginState: LoginState,
    onEvent: (BlogEvent) -> Unit = {},
) {

    BackHandler {
        onEvent(BlogEvent.OnArticleClick(null))
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                        .background(Color.Gray, shape = CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "person"
                    )
                }
                Text(
                    text = "Welcome, ${loginState.username}",
                    fontSize = 20.sp,
                )
            }

        }
        AsyncImage(
            blogState.selectedArticle?.imgUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )
        Text(
            text = blogState.selectedArticle?.title ?: "No Title",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = blogState.selectedArticle?.author ?: "No Author",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = blogState.selectedArticle?.body ?: "No Body",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleScreenPreview() {
    ArticleScreen(
        blogState = BlogState(
            selectedArticle = getArticle().random()
        ),
        loginState = LoginState(
            username = "Rahul"
        )
    )
}