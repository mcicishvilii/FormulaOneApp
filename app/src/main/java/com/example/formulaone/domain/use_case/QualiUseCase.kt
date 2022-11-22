package com.example.formulaone.domain.use_case


import android.util.Log
import com.example.formulaone.domain.model.QualiDomain
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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