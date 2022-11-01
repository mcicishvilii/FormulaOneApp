package com.example.formulaone.data.repository_impl


import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.domain.repository.remote.LastRaceRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class LastRaceImpl @Inject constructor(
    private val api: RaceService
): LastRaceRepository {

//citcuit miweria circuit unda iyos
    override suspend fun getLastRaceCictuit(): LastRaceDto {
        return api.getLastRaceInfo()
    }

    override suspend fun getLastRaceWinner(): LastRaceDto {
        return api.getLastRaceInfo()
    }


}