package com.example.formulaone.domain.use_case


import com.example.formulaone.domain.model.QualiDomain
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class QualiUseCase @Inject constructor(
    private val repository: CurrentDriversStandingsRepository
) {
    operator fun invoke(): Flow<Resource<List<QualiDomain>>> = channelFlow {
        repository.getQualiData().collectLatest {
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