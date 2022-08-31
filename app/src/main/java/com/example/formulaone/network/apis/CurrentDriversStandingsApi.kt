package com.example.formulaone.network.apis

import com.example.formulaone.models.driversResponse.CurrentDriversStandingsResponse
import com.example.formulaone.models.driversResponse.fromPlugin.*
import com.example.formulaone.models.teamsResponse.Teams
import retrofit2.http.GET

interface CurrentDriversStandingsApi {
    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
    ): United
}