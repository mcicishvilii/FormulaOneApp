package com.example.formulaone.data.repository

import com.example.formulaone.data.model.raceSchedule.ToRaceScheduleDomain
import com.example.formulaone.domain.model.RaceScheduleDomain
import com.example.formulaone.domain.repository.RacesSheduleRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class RaceScheduleRepositoryImpl @Inject constructor(
    private val api: RaceService
): RacesSheduleRepository {
    override suspend fun getRacesShceduleData(): List<RaceScheduleDomain> {
        return api.getLastRacesSchedule().MRData.RaceTable.Races.map { it.ToRaceScheduleDomain() }

    }
}


