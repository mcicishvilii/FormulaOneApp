package com.example.formulaone.domain.use_case.weather

import com.example.formulaone.common.Resource
import com.example.formulaone.data.model.weather.WeatherDataDto
import com.example.formulaone.data.repository.RaceScheduleRepositoryImpl
import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class GetWeatherUseCase @Inject constructor(
    private val repository: RaceScheduleRepositoryImpl,
){
    operator fun invoke(lat:Double,long:Double): Flow<Resource<WeatherDataDto>> = flow {
        try {
            emit(Resource.Loading(true))
            val answer = repository.getWeatherData(lat,long)
            emit(Resource.Success(answer))
        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected"))
        }
        catch (e: IOException){
            emit(Resource.Error("couldn't reach server"))
        }
    }
}