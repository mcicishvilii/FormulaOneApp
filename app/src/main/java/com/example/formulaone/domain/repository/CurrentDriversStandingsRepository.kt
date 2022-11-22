package com.example.formulaoneapplicationn.domain.repository

import com.example.formulaone.domain.model.QualiDomain
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import kotlinx.coroutines.flow.Flow

// not using yet. for displaying the full list of current driver standings

interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): Flow<Resource<List<DriverStandingDomain>>>
    suspend fun getQualiData(): Flow<Resource<List<QualiDomain>>>
    suspend fun asd(): Flow<Resource<List<QualiDomain>>>
}