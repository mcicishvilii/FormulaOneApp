package com.example.formulaone.network.apis

import com.example.formulaone.data.drivers.last_race.Circuit
import com.example.formulaone.data.drivers.last_race.Driver
import com.example.formulaone.data.drivers.last_race.Race
import retrofit2.http.GET

interface LastRaceAPi {
    @GET("current/last/results.json")
    suspend fun getLastRaceCircuit(
    ): List<Race>

    @GET("current/last/results.json")
    suspend fun getLastRaceWinner(
    ): Driver
}