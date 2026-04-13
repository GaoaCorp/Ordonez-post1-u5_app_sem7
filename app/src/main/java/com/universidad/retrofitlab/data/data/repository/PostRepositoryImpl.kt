package com.universidad.retrofitlab.data.repository

import com.universidad.retrofitlab.data.remote.api.PostApi
import com.universidad.retrofitlab.data.remote.dto.toDomain
import com.universidad.retrofitlab.domain.model.Post
import com.universidad.retrofitlab.domain.repository.PostRepository

class PostRepositoryImpl(private val api: PostApi) : PostRepository {
    override suspend fun getPosts(page: Int): Result<List<Post>> =
        runCatching { api.getPosts(page = page).map { it.toDomain() } }
}