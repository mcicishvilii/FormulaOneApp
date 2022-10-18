package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import retrofit2.http.GET
import retrofit2.http.Query


interface CurrentDriversStandingsApi {
    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
        @Query("limit")
        limit: String,
    ): DriverStandingsDto
}