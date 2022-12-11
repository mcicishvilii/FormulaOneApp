package com.example.formulaone.domain.use_case.teams

import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import com.example.formulaone.domain.repository.TeamsRepository
import javax.inject.Inject

class InsertTeamUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend operator fun invoke(team: TeamsDomain) {
        repository.insertTeamIntoDb(team)
    }
}