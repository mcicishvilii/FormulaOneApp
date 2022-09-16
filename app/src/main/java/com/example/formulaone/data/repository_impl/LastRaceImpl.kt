package com.example.formulaone.data.repository_impl

import com.example.formulaone.data.drivers.last_race.Driver
import com.example.formulaone.data.drivers.last_race.Race
import com.example.formulaone.domain.repository.LastRaceRepository
import com.example.formulaone.network.apis.LastRaceAPi
import javax.inject.Inject

class LastRaceImpl @Inject constructor(
    private val api:LastRaceAPi
):LastRaceRepository {
    override suspend fun getLastRaceInfo(): List<Race> {
        return api.getLastRaceCircuit()
    }

    override suspend fun getLastRaceWinner(): Driver {
        return api.getLastRaceWinner()
    }

}