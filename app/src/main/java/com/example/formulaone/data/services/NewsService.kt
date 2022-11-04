package com.example.formulaone.data.services

import com.example.formulaone.data.model.news.new_api.F1NewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getNews(
        @Query("q")
        q: String,
        @Query("apiKey")
        apiKey: String,
    ): Response<F1NewsDto>

}