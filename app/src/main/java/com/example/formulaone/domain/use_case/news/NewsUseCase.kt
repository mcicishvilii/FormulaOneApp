package com.example.formulaone.domain.use_case.news


import android.util.Log
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class NewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<ArticleDomain>>> = channelFlow {
        repository.getNews().collectLatest {
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