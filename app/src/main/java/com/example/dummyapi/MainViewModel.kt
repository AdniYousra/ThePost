package com.example.dummyapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapi.delete.Delete
import com.example.dummyapi.data.Data
import com.example.dummyapi.data.PostData
import com.example.dummyapi.data.Posts

import com.example.dummyapi.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

// ini Api final functions
class MainViewModel(private val repository: PostRepository): ViewModel() {
    val PostResponse: MutableLiveData<Response<PostData>> = MutableLiveData()
    val CreatePostResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    val Response: MutableLiveData<Response<Posts>> = MutableLiveData()
    val DeletePostResponse: MutableLiveData<Response<Delete>> = MutableLiveData()




    fun getPost(postId: String) {
        viewModelScope.launch {
            val response = repository.getPost(postId)
            PostResponse.value = response
        }
    }
    fun getPosts(pageNumber: Int, postsNumber: Int) {
        viewModelScope.launch {
            val response = repository.getPosts(pageNumber, postsNumber)
            Response.value = response
        }
    }

    fun getPostsByTag(tag: String, postsNumber: Int) {
        viewModelScope.launch {
            val response = repository.getPostsByTag(tag, postsNumber)
            Response.value = response
        }
    }

    fun deletePost(postId: String) {
        viewModelScope.launch {
            val response = repository.deletePost(postId)
            DeletePostResponse.value = response
        }
    }

    fun createPost(text: String, image: String,likes: Int, tags: ArrayList<String>, owner: String) {
        viewModelScope.launch {
            val response = repository.createPost(text, image,likes, tags ,owner)
            CreatePostResponse.value = response
        }
    }
}