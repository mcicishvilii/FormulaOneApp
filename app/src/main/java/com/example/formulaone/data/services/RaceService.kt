package com.example.formulaoneapplicationn.data.services

import com.example.formulaone.data.model.drivers.quali.QualiResultDto
import com.example.formulaone.data.model.raceResults.RaceResultsDto
import com.example.formulaone.data.model.raceSchedule.RaceScheduleDto
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.RealDriverStandingsDto
import com.example.formulaoneapplicationn.data.model.last_race.LastRaceDto
import com.example.formulaoneapplicationn.data.model.teams.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RaceService {
    @GET("current.json")
    suspend fun getLastRacesSchedule(
    ): Response<RaceScheduleDto>

    @GET("current/qualifying/1.json")
    suspend fun getQualiResults(
    ): Response<QualiResultDto>

    @GET("current/last/results.json")
    suspend fun getLastRaceInformation(
    ): Response<LastRaceDto>

    @GET("current/results.json")
    suspend fun getLastRaceDetails(
        @Query("limit")
        limit: String,
    ): Response<RaceResultsDto>

    @GET("constructors.json")
    suspend fun getTeamsPAging(
        @Query("limit")
        limit: String,
        @Query("offset")
        offset: String,
    ): Response<Teams>

    @GET("constructors.json?limit=200")
    suspend fun getTeams(
    ): Response<Teams>

    @GET("current/driverStandings.json")
    suspend fun getCurrentStandings(
        @Query("limit")
        limit: String,
    ): Response<RealDriverStandingsDto>






}