package com.example.formulaone.domain.use_case.links

import com.example.formulaone.domain.model.LinksDomain
import com.example.formulaone.domain.repository.LinksRepository
import com.example.formulaoneapplicationn.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class LinksUseCase @Inject constructor(
    private val repository: LinksRepository
) {
    operator fun invoke(): Flow<Resource<List<LinksDomain>>> = channelFlow {
        repository.getLinks().collectLatest {
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