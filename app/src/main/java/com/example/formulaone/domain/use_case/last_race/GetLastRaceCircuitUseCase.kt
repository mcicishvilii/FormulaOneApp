package com.example.formulaone.domain.use_case.last_race

import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.data.drivers.last_race.Driver
import com.example.formulaone.domain.repository.LastRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLastRaceCircuitUseCase @Inject constructor(
    private val repository: LastRaceRepository
) {
    operator fun invoke(): Flow<Resource<Driver>> = flow{
        try {
            emit(Resource.Loading(true))
            val circuit = repository.getLastRaceWinner()
            emit(Resource.Success(circuit))
        }
        catch (e:HttpException){
            Log.d("tag", "error")
        }
        catch (e:IOException){
            Log.d("tag", "io error")
        }
    }
}