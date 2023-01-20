package com.example.formulaoneapplicationn.domain.repository

import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import kotlinx.coroutines.flow.Flow


interface LastRaceRepository {

    suspend fun getLastRaceInfo(): Flow<Resource<List<LastRaceInfoDomain>>>


}