package com.example.formulaone.domain.use_case.favs


import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import com.example.formulaone.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavTeamsUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend operator fun invoke(): Flow<List<TeamsDomain>> {
        return repository.getTeams()
    }
}