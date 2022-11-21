package com.example.formulaone.domain.use_case.weather

import android.util.Log
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.weather.WeatherDataDto
import com.example.formulaoneapplicationn.data.repository.RaceScheduleRepositoryImpl
import com.example.formulaoneapplicationn.domain.model.DailyDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetWeatherUseCase @Inject constructor(
    private val repository: RaceScheduleRepositoryImpl,
){
    operator fun invoke(lat:Double,long:Double): Flow<Resource<DailyDomain>> = channelFlow {
        repository.getWeatherData(lat, long).collectLatest {
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