package com.example.formulaone.domain.repository

import com.example.formulaone.data.drivers.last_race.*
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto

interface LastRaceRepository {

    suspend fun getLastRaceInfo(): LastRaceDto

}