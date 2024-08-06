package com.digi.composemvvm.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.digi.composemvvm.models.Article

sealed class BlogEvent {
    data class OnArticleClick(val article: Article) : BlogEvent()
}

@Composable
fun BlogScreen(
    modifier: Modifier = Modifier,
    blogState: BlogState,
    loginState: LoginState,
    onEvent: (BlogEvent) -> Unit = {},
) {
    Column(
        modifier = modifier
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(blogState.articleList) { article ->
                ArticleCard(article = article) { selectedArticle ->

                }
            }
        }
    }
}

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleSelected: (Article) -> Unit,
) {
    Card(
        onClick = { onArticleSelected(article) },
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                article.imgUrl,
                contentDescription = article.title,
                modifier = Modifier.height(300.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 0f,
                            endY = 150f,
                        )
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = article.title, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = article.author,
                        fontSize = 12.sp,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            modifier = Modifier.size(16.dp),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${article.likes}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BlogScreenPreview() {
    BlogScreen(
        blogState = BlogState(),
        loginState = LoginState(
            username = "Rahul"
        ),
        onEvent = {}
    )
}