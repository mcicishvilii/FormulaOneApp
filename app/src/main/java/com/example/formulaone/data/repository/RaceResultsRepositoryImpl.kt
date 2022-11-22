package com.example.formulaoneapplicationn.data.repository

import com.example.formulaone.data.model.raceResults.toRaceDomain
import com.example.formulaoneapplicationn.domain.model.RaceDomain
import com.example.formulaone.domain.repository.RaceResultsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RaceResultsRepositoryImpl @Inject constructor(
    private val api: RaceService
): RaceResultsRepository {
    override suspend fun GetRaceResultsRepository(): Flow<Resource<List<RaceDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getLastRaceDetails("600")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.MRData.RaceTable.Races.map { it.toRaceDomain()}))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}