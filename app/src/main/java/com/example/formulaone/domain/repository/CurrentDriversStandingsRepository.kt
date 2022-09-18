package com.example.formulaone.domain.repository

import com.example.formulaone.data.drivers.plugin.DriverStandingsDto

// not using yet. for displaying the full list of current driver standings
interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): DriverStandingsDto
}