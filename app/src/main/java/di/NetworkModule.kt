package com.universidad.retrofitlab.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.universidad.retrofitlab.data.remote.api.PostApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val req = chain.request().newBuilder()
                .header("Accept", "application/json")
                .header("X-App-Version", "1.0.0")
                .header("Authorization", "Bearer demo-token-u5")
                .build()
            chain.proceed(req)
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val postApi: PostApi = retrofit.create(PostApi::class.java)
}