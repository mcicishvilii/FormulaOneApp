package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.TeamsDtoLocal
import javax.inject.Inject

class InsertTeamUseCase @Inject constructor(
    private val repository: TeamsRepositoryLocal
) {
    suspend operator fun invoke(team: TeamsDtoLocal) {
        repository.insertTeamIntoDb(team)
    }
}