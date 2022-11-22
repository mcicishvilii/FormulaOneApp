package com.example.formulaone.domain.use_case.last_race


import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import com.example.formulaoneapplicationn.domain.repository.LastRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


// using to display all the teams in teams fragment
class GetLastRaceInformationUseCase @Inject constructor(
    private val repository: LastRaceRepository
) {
    operator fun invoke(): Flow<Resource<List<LastRaceInfoDomain>>> = channelFlow {
        repository.getLastRaceInfo().collectLatest {
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