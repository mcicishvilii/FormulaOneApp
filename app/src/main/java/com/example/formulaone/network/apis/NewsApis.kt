package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.drivers_standings.DriverStandingsDto
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.data.remote.raceResults.RaceResultsDto
import com.example.formulaone.data.remote.raceSchedule.RaceScheduleDto
import com.example.formulaone.data.remote.teams.Teams
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApis {
    @GET("current.json")
    suspend fun getNews(
    ): RaceScheduleDto


}