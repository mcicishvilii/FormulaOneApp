package com.example.formulaone.data.repository_impl

import com.example.formulaone.data.remote.raceSchedule.ToRaceScheduleDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.repository.remote.RacesSheduleRepository
import com.example.formulaone.data.services.RaceService
import javax.inject.Inject

class RaceScheduleRepositoryImpl @Inject constructor(
    private val api: RaceService
):RacesSheduleRepository {
    override suspend fun getRacesShceduleData(): List<RaceScheduleDomain> {
        return api.getLastRacesSchedule().MRData.RaceTable.Races.map { it.ToRaceScheduleDomain() }

    }
}


