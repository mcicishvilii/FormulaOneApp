package com.example.formulaone.data.services

import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.data.remote.raceResults.RaceResultsDto
import com.example.formulaone.data.remote.raceSchedule.RaceScheduleDto
import com.example.formulaone.data.remote.teams.Teams
import retrofit2.http.GET
import retrofit2.http.Query

interface RaceService {
    @GET("current.json")
    suspend fun getLastRacesSchedule(
    ): RaceScheduleDto

    @GET("current/results.json")
    suspend fun getLastRaceDetails(
        @Query("limit")
        limit: String,
    ): RaceResultsDto

    @GET("constructors.json?limit=211")
    suspend fun getDriversList(
    ): Teams

    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
        @Query("limit")
        limit: String,
    ): DriverStandingsDto

    @GET("current/last/results.json")
    suspend fun getLastRaceInfo(
    ): LastRaceDto

}