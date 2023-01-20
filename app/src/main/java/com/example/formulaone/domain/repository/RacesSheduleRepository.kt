package com.example.formulaone.domain.repository

import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.DailyDomain
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import kotlinx.coroutines.flow.Flow

interface RacesSheduleRepository {
    suspend fun getRacesShceduleData(): Flow<Resource<List<RaceScheduleDomain>>>
    suspend fun getWeatherData(lat:Double,long:Double): Flow<Resource<DailyDomain>>
}