package com.example.formulaone.domain.use_case.last_race

import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.data.drivers.last_race.RaceTable
import com.example.formulaone.domain.repository.LastRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLastRaceSeasonUseCase @Inject constructor(
    private val repository: LastRaceRepository
) {
    operator fun invoke(): Flow<Resource<RaceTable>> = flow{
        try {
            emit(Resource.Loading(true))
            val circuit = repository.getLastRaceInfo()
            emit(Resource.Success(circuit))
        }
        catch (e: HttpException){
            Log.d("tag", "error")
        }
        catch (e: IOException){
            Log.d("tag", "io error")
        }
    }
}