package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.plugin.DriverStandingsDto
import retrofit2.http.GET


// not using yet
interface CurrentDriversStandingsApi {
    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
    ): DriverStandingsDto
}