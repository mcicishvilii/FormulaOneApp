package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetTeamsListUseCase @Inject constructor(
    private val repository: TeamsRepository
){
    operator fun invoke(): Flow<Resource<List<TeamsDomain>>> = channelFlow {
        repository.getTeamsData().collectLatest {
            when (it){
                is Resource.Success -> {
                    send(Resource.Success(it.data))
                }
                is Resource.Error -> {
                    send(Resource.Error(it.error))
                }
                is Resource.Loading -> {
                    send(Resource.Loading(it.loading))
                }
            }
        }
    }
}