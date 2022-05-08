package com.example.dummyapi.data

data class PostData (
    val id: String,
    val image: String,
    val likes: Int,
    val link: String,
    val tags: ArrayList<String>,
    val text: String,
    val publishDate: String,
    val owner: Owner
)