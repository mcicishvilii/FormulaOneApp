package com.example.formulaone.domain.repository

import com.example.formulaone.data.model.drivers.drivers_standings.DriverStandingsDto

// not using yet. for displaying the full list of current driver standings
interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): com.example.formulaone.data.model.drivers.drivers_standings.DriverStandingsDto
}