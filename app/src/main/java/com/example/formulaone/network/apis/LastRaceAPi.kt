package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.plugin.DriverStandingsDto
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import retrofit2.http.GET


// using
interface LastRaceAPi {
    @GET("current/last/results.json")
    suspend fun getLastRaceInfo(
    ): LastRaceDto
}