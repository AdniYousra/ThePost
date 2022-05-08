package com.example.dummyapi.repository

import com.example.dummyapi.rest.RetrofitInstance
import com.example.dummyapi.delete.Delete
import com.example.dummyapi.data.Data
import com.example.dummyapi.data.PostData
import com.example.dummyapi.data.Posts
import retrofit2.Response

class PostRepository {

    suspend fun getPosts(pageNumber: Int, postsNumber: Int): Response<Posts> {
        return RetrofitInstance.api.getPosts(pageNumber, postsNumber)
    }

    suspend fun getPost(postId: String): Response<PostData> {
        return RetrofitInstance.api.getPost(postId)
    }

    suspend fun getPostsByTag(tag: String, postsNumber: Int): Response<Posts> {
        return RetrofitInstance.api.getPostsByTag(tag, postsNumber)
    }

    suspend fun deletePost(postId: String): Response<Delete> {
        return RetrofitInstance.api.deletePost(postId)
    }

    suspend fun createPost(text: String, image: String,likes: Int, tags: ArrayList<String>, owner: String): Response<Data> {
        return RetrofitInstance.api.createPost(text, image,likes, tags ,owner)
    }
}