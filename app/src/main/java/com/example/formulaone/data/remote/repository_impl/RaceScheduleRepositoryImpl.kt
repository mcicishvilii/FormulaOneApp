package com.example.formulaone.data.remote.repository_impl

import com.example.formulaone.data.remote.raceSchedule.ToRaceScheduleDomain
import com.example.formulaone.data.remote.teams.ToTeamsDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.repository.remote.RacesSheduleRepository
import com.example.formulaone.network.apis.ConstructorsApi
import com.example.formulaone.network.apis.RaceScheduleApi
import javax.inject.Inject

class RaceScheduleRepositoryImpl @Inject constructor(
    private val api: RaceScheduleApi
):RacesSheduleRepository {
    override suspend fun getRacesShceduleData(): List<RaceScheduleDomain> {
        return api.getLastRacesSchedule().MRData.RaceTable.Races.map { it.ToRaceScheduleDomain() }

    }
}


