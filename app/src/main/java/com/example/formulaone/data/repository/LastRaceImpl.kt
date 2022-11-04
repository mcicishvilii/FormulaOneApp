package com.example.formulaone.data.repository


import com.example.formulaone.domain.repository.LastRaceRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class LastRaceImpl @Inject constructor(
    private val api: RaceService
): LastRaceRepository {

//citcuit miweria circuit unda iyos
    override suspend fun getLastRaceCictuit(): com.example.formulaone.data.model.drivers.last_race.LastRaceDto {
        return api.getLastRaceInfo()
    }

    override suspend fun getLastRaceWinner(): com.example.formulaone.data.model.drivers.last_race.LastRaceDto {
        return api.getLastRaceInfo()
    }


}