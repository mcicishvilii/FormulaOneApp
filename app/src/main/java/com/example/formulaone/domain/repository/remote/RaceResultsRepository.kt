package com.example.formulaone.domain.repository.remote

import com.example.formulaone.domain.model.remote.RaceDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain

interface RaceResultsRepository {
    suspend fun GetRaceResultsRepository(): List<RaceDomain>
}