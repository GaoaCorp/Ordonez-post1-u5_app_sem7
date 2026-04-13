package com.universidad.retrofitlab.data.remote.api

import com.universidad.retrofitlab.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int = 1,
        @Query("_limit") limit: Int = 20
    ): List<PostDto>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): PostDto
}