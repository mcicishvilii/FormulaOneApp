package com.example.formulaone.domain.repository

import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.RaceDomain
import kotlinx.coroutines.flow.Flow

interface RaceResultsRepository {
    suspend fun GetRaceResultsRepository(): Flow<Resource<List<RaceDomain>>>
}