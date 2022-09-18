package com.example.formulaone.data.repository_impl

import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: CurrentDriversStandingsApi
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): DriverStandingsDto {
        return api.getCurrentStandings()
    }


}