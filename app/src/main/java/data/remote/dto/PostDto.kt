package com.universidad.retrofitlab.data.remote.dto

import com.universidad.retrofitlab.domain.model.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int,
    @SerialName("userId") val userId: Int,
    val title: String,
    val body: String
)

fun PostDto.toDomain(): Post = Post(
    id = id,
    userId = userId,
    title = title,
    excerpt = body.take(100).trimEnd() + if (body.length > 100) "…" else ""
)