package com.example.formulaone.data.remote.repository_impl

import com.example.formulaone.domain.repository.remote.CurrentDriversStandingsRepository
import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.network.apis.RaceApis
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: RaceApis
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): DriverStandingsDto {
        return api.getCurrentStandings("300")
    }
}