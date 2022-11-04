package com.example.formulaone.domain.repository

import com.example.formulaone.domain.model.RaceDomain

interface RaceResultsRepository {
    suspend fun GetRaceResultsRepository(): List<RaceDomain>
}