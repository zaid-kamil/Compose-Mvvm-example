package com.digi.composemvvm.models

data class Article(
    val imgUrl: String,
    val title: String,
    val body: String,
    val author: String = "Zaid kamil",
    val likes: Int,
)
