package com.example.formulaone.data.remote.repository_impl

import com.example.formulaone.domain.repository.remote.CurrentDriversStandingsRepository
import com.example.formulaone.data.remote.drivers.plugin.DriverStandingsDto
import com.example.formulaone.network.apis.CurrentDriversStandingsApi
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val api: CurrentDriversStandingsApi
) : CurrentDriversStandingsRepository {


    override suspend fun getCurrentDriversStanding(): DriverStandingsDto {
        return api.getCurrentStandings()
    }


}