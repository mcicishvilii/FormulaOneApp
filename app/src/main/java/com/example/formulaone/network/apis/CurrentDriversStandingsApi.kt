package com.example.formulaone.network.apis

import com.example.formulaone.models.drivers.plugin.PluginStandings
import retrofit2.http.GET

interface CurrentDriversStandingsApi {
    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
    ): PluginStandings
}