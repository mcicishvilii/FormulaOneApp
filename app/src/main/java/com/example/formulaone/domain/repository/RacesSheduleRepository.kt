package com.example.formulaone.domain.repository

import com.example.formulaone.domain.model.RaceScheduleDomain

interface RacesSheduleRepository {
    suspend fun getRacesShceduleData(): List<RaceScheduleDomain>
}