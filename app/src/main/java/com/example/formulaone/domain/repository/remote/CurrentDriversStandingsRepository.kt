package com.example.formulaone.domain.repository.remote

import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto

// not using yet. for displaying the full list of current driver standings
interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): DriverStandingsDto
}