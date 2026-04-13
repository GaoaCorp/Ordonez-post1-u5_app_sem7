package com.universidad.retrofitlab.domain.repository

import com.universidad.retrofitlab.domain.model.Post

interface PostRepository {
    suspend fun getPosts(page: Int): Result<List<Post>>
}