package com.example.formulaone.data.services

import com.example.formulaone.data.model.news.new_api.F1NewsDto
import com.example.formulaone.data.model.weather.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude")
        latitude: String,
        @Query("longitude")
        longitude: String,
        @Query("daily")
        daily: String,
        @Query("timezone")
        timezone: String,
    ): Response<WeatherDataDto>

}