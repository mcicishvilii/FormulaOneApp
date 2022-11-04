package com.example.formulaone.data.repository

import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: RaceService
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): com.example.formulaone.data.model.drivers.drivers_standings.DriverStandingsDto {
        return api.getCurrentStandings("300")
    }
}