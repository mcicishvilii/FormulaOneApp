package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.data.local.TeamsDtoLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamsFromDBUseCase @Inject constructor(
    private val repository: TeamsRepositoryLocal
) {
    operator fun invoke(): Flow<List<TeamsDtoLocal>> {
        return repository.getTeams()
    }
}