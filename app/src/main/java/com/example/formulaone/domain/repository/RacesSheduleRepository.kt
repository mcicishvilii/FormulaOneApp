package com.example.formulaone.domain.repository

import com.example.formulaone.common.Resource
import com.example.formulaone.data.model.weather.WeatherDataDto
import com.example.formulaone.domain.model.RaceScheduleDomain
import retrofit2.Response

interface RacesSheduleRepository {
    suspend fun getRacesShceduleData(): List<RaceScheduleDomain>
    suspend fun getWeatherData(lat:Double,long:Double): WeatherDataDto
}