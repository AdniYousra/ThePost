package com.example.dummyapi.rest

import com.example.dummyapi.adapter.Api
import com.example.dummyapi.model.main.Link.Companion.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}