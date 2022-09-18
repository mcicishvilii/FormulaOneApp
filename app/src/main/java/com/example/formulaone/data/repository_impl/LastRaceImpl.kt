package com.example.formulaone.data.repository_impl

import com.example.formulaone.data.drivers.last_race.*
import com.example.formulaone.domain.repository.LastRaceRepository
import com.example.formulaone.network.apis.LastRaceAPi
import javax.inject.Inject

class LastRaceImpl @Inject constructor(
    private val api:LastRaceAPi
):LastRaceRepository {


    override suspend fun getLastRaceCictuit(): LastRaceDto {
        return api.getLastRaceInfo()
    }

    override suspend fun getLastRaceWinner(): LastRaceDto {
        return api.getLastRaceInfo()
    }


}