package com.example.formulaone.data.repository

import com.example.formulaone.data.model.raceResults.toRaceDomain
import com.example.formulaone.domain.model.RaceDomain
import com.example.formulaone.domain.repository.RaceResultsRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class RaceResultsRepositoryImpl @Inject constructor(
    private val api: RaceService
): RaceResultsRepository {
    override suspend fun GetRaceResultsRepository(): List<RaceDomain> {
        return api.getLastRaceDetails("360").MRData.RaceTable.Races.map{it.toRaceDomain()}
    }

}