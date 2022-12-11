package com.example.formulaoneapplicationn.domain.use_case.drivers

import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class CurrentDriversStandingsUseCase @Inject constructor(
    private val repository: CurrentDriversStandingsRepository
){
    operator fun invoke(): Flow<Resource<List<DriverStandingDomain>>> = channelFlow {
        repository.getCurrentDriversStanding().collectLatest {
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