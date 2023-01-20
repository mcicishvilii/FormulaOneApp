package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.data.model.raceSchedule.ToRaceScheduleDomain
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import com.example.formulaone.domain.repository.RacesSheduleRepository
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.data.model.weather.toDomain
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.services.WeatherService
import com.example.formulaoneapplicationn.domain.model.DailyDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RaceScheduleRepositoryImpl @Inject constructor(
    private val api: RaceService,
    private val weatherApi: WeatherService
): RacesSheduleRepository {

    override suspend fun getRacesShceduleData(): Flow<Resource<List<RaceScheduleDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getLastRacesSchedule()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.MRData.RaceTable.Races.map{it.ToRaceScheduleDomain()}))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun getWeatherData(lat: Double, long: Double): Flow<Resource<DailyDomain>>  = flow {
        try {
            emit(Resource.Loading(true))
            val response = weatherApi.getForecast(lat,long,listOf("temperature_2m_max","precipitation_sum","weathercode"),"Europe/Moscow")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.daily.toDomain()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}


