package com.example.formulaone.network.apis

import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.data.remote.raceSchedule.Race
import com.example.formulaone.data.remote.raceSchedule.RaceScheduleDto
import retrofit2.http.GET

interface RaceScheduleApi {
    @GET("2022.json")
    suspend fun getLastRacesSchedule(
    ): RaceScheduleDto
}