package com.example.formulaone.domain.repository

import com.example.formulaone.data.drivers.plugin.DriverStandingsDto

interface CurrentDriversStandingsRepository {
    suspend fun getCurrentDriversStanding(): DriverStandingsDto
}