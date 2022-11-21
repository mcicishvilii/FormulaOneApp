package com.example.formulaoneapplicationn.data.services

import com.example.formulaoneapplicationn.data.model.weather.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude")
        latitude: Double,
        @Query("longitude")
        longitude: Double,
        @Query("daily")
        daily: List<String>,
        @Query("timezone")
        timezone: String,
    ): Response<WeatherDataDto>

}