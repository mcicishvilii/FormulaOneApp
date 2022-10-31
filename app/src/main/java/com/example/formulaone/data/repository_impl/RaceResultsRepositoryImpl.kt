package com.example.formulaone.data.repository_impl

import com.example.formulaone.data.remote.raceResults.toRaceDomain
import com.example.formulaone.domain.model.remote.RaceDomain
import com.example.formulaone.domain.repository.remote.RaceResultsRepository
import com.example.formulaone.network.apis.RaceApis
import javax.inject.Inject

class RaceResultsRepositoryImpl @Inject constructor(
    private val api: RaceApis
):RaceResultsRepository {
    override suspend fun GetRaceResultsRepository(): List<RaceDomain> {
        return api.getLastRaceDetails("360").MRData.RaceTable.Races.map{it.toRaceDomain()}
    }

}