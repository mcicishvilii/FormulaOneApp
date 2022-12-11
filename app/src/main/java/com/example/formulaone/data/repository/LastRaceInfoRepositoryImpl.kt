package com.example.formulaoneapplicationn.data.repository

import android.util.Log
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.data.model.drivers.drivers_standings.toDomain
import com.example.formulaoneapplicationn.data.model.last_race.toLastRaceInfoDomain
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import com.example.formulaoneapplicationn.domain.repository.LastRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LastRaceInfoRepositoryImpl @Inject constructor(
    private val api: RaceService
) : LastRaceRepository {
    override suspend fun getLastRaceInfo(): Flow<Resource<List<LastRaceInfoDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getLastRaceInformation()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.mRData.raceTable.races.map { it.toLastRaceInfoDomain() }))
                Log.d("ciciko",response.body().toString())
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}