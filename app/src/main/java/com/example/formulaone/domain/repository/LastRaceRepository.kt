package com.example.formulaoneapplicationn.domain.repository

import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.last_race.LastRaceDto
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import kotlinx.coroutines.flow.Flow


interface LastRaceRepository {

    suspend fun getLastRaceInfo(): Flow<Resource<List<LastRaceInfoDomain>>>


}