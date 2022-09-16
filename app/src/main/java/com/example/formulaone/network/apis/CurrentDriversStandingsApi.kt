package com.example.formulaone.network.apis

import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import retrofit2.http.GET

interface CurrentDriversStandingsApi {
    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
    ): DriverStandingsDto
}