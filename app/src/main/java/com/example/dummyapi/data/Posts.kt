package com.example.dummyapi.data

data class Posts (
    val data : ArrayList<Data>,
    val total: Int,
    val page: Int,
    val limit: Int
)