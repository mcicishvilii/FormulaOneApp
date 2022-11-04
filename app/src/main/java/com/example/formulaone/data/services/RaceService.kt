package com.example.formulaone.data.services

import com.example.formulaone.data.model.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.model.drivers.last_race.LastRaceDto
import com.example.formulaone.data.model.raceResults.RaceResultsDto
import com.example.formulaone.data.model.raceSchedule.RaceScheduleDto
import com.example.formulaone.data.model.teams.Teams
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
    ): com.example.formulaone.data.model.drivers.drivers_standings.DriverStandingsDto

    @GET("current/last/results.json")
    suspend fun getLastRaceInfo(
    ): com.example.formulaone.data.model.drivers.last_race.LastRaceDto

}