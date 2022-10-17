package com.example.formulaone.domain.use_case.schedule

import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.domain.model.remote.RaceDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.repository.remote.RaceResultsRepository
import com.example.formulaone.domain.repository.remote.RacesSheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RaceDetailsUseCase @Inject constructor(
    private val repository: RaceResultsRepository
) {
    operator fun invoke(): Flow<Resource<List<RaceDomain>>> = flow{
        try {
            emit(Resource.Loading(true))
            val raceData = repository.GetRaceResultsRepository()
            Log.d("reisingi",raceData.toString())
            emit(Resource.Success(raceData))
        }
        catch (e: HttpException){
            Log.d("tag", "error")
        }
        catch (e: IOException){
            Log.d("tag", "io error")
        }
    }
}