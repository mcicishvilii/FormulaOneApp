package com.example.formulaone.network.apis

import com.example.formulaone.data.drivers.last_race.*
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import retrofit2.http.GET


// using
interface LastRaceAPi {
    @GET("current/last/results.json")
    suspend fun getLastRaceInfo(
    ): LastRaceDto
}