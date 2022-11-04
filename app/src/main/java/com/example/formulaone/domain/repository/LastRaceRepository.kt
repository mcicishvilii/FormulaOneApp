package com.example.formulaone.domain.repository

import com.example.formulaone.data.model.drivers.last_race.LastRaceDto

interface LastRaceRepository {

    suspend fun getLastRaceCictuit(): com.example.formulaone.data.model.drivers.last_race.LastRaceDto

    suspend fun getLastRaceWinner(): com.example.formulaone.data.model.drivers.last_race.LastRaceDto

}