package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.models.TeamsDtoLocal
import javax.inject.Inject

class DeleteTeamUseCase @Inject constructor(
    private val repository: TeamsRepositoryLocal
) {
    suspend operator fun invoke(team: TeamsDtoLocal) {
        repository.deleteTeam(team)
    }
}