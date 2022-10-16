package com.example.formulaone.domain.repository.remote

import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain

interface RacesSheduleRepository {
    suspend fun getRacesShceduleData(): List<RaceScheduleDomain>
}