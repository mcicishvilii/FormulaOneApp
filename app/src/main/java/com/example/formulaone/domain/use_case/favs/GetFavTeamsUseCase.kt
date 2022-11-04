package com.example.formulaone.domain.use_case.favs


import com.example.formulaone.domain.model.TeamsDomain
import com.example.formulaone.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// using to display all the teams in teams fragment
class GetFavTeamsUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend operator fun invoke(): Flow<List<TeamsDomain>> {
        return repository.getTeams()
    }
}