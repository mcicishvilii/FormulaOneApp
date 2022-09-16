package com.example.formulaone.domain.repository

import com.example.formulaone.data.drivers.last_race.Driver
import com.example.formulaone.data.drivers.last_race.Race

interface LastRaceRepository {
    suspend fun getLastRaceInfo():List<Race>

    suspend fun getLastRaceWinner():Driver
}