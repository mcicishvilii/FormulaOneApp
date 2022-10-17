package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.data.remote.raceResults.RaceResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RaceResultsApi {
    @GET("current/last/results.json")
    suspend fun getLastRaceDetails(
        @Query("limit")
        limit: String,
    ): RaceResultsDto
}