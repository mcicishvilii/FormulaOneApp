package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.teams.Teams
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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