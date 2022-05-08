package com.example.dummyapi.adapter
import com.example.dummyapi.delete.Delete
import com.example.dummyapi.data.Data
import com.example.dummyapi.data.PostData
import com.example.dummyapi.data.Posts
import retrofit2.Response
import retrofit2.http.*


interface Api {
    @Headers("app-id: 62596247fa6579ed41b683e5")
    @GET("post")

    suspend fun getPosts(
        @Query("page") pageNumber: Int,
        @Query("limit") postsNumber: Int
    ): Response<Posts>

    @Headers("app-id: 62596247fa6579ed41b683e5")
    @GET("post/{post}")
    suspend fun getPost(
        @Path("post") postId: String
    ): Response<PostData>

    @Headers("app-id: 62596247fa6579ed41b683e5")
    @GET("tag/{tag}/post")
    suspend fun getPostsByTag(
        @Path("tag") tag: String,
        @Query("limit") postsNumber: Int
    ): Response<Posts>

    @Headers("app-id: 62596247fa6579ed41b683e5")
    @DELETE("post/{post}")
    suspend fun deletePost(
        @Path("post") postId: String
    ): Response<Delete>

    @FormUrlEncoded
    @Headers("app-id: 62596247fa6579ed41b683e5")
    @POST("post/create")
    suspend fun createPost(
        @Field("text") text: String,
        @Field("image") image: String,
        @Field("likes") likes: Int,
        @Field("tags") tags: ArrayList<String>,
        @Field("owner") owner: String,
    ): Response<Data>

}