package com.example.formulaone.domain.repository.remote

import com.example.formulaone.data.remote.drivers.plugin.DriverStandingsDto
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto

interface LastRaceRepository {

    suspend fun getLastRaceCictuit(): LastRaceDto

    suspend fun getLastRaceWinner(): LastRaceDto

}