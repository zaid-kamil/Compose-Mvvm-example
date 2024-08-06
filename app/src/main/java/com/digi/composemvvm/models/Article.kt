package com.digi.composemvvm.models

data class Article(
    val imgUrl: String,
    val title: String,
    val body: String,
    val author: String = "Zaid kamil",
    val likes: Int,
)


fun getArticle(): List<Article> {
    return listOf(
        Article(
            imgUrl = "https://picsum.photos/id/10/400/200",
            title = "Article One",
            body = "This is the body of article one",
            likes = 100
        ),
        Article(
            imgUrl = "https://picsum.photos/id/11/400/200",
            title = "Article Two",
            body = "This is the body of article two",
            likes = 100,
            author = "Noor ul Amaan"
        ),
        Article(
            imgUrl = "https://picsum.photos/id/12/400/200",
            title = "Article Three",
            body = "This is the body of article three",
            likes = 100,
            author = "Alex Winter"
        ),
        Article(
            imgUrl = "https://picsum.photos/id/13/400/200",
            title = "Article Four",
            body = "This is the body of article four",
            likes = 100
        ),
        Article(
            imgUrl = "https://picsum.photos/id/14/400/200",
            title = "Article Five",
            body = "This is the body of article Five",
            likes = 100,
            author = "Vijay Sen"
        ),
        Article(
            imgUrl = "https://picsum.photos/id/15/400/200",
            title = "Article Six",
            body = "This is the body of article six",
            likes = 100,
            author = "Sunny Singh"
        ),
        Article(
            imgUrl = "https://picsum.photos/id/16/400/200",
            title = "Article Seven",
            body = "This is the body of article seven",
            likes = 100
        ),
    )
}