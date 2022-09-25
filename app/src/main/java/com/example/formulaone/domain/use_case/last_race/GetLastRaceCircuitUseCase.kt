package com.example.formulaone.domain.use_case.last_race

import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.domain.repository.remote.LastRaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


// using as the name suggests
class GetLastRaceCircuitUseCase @Inject constructor(
    private val repository: LastRaceRepository
) {
    operator fun invoke(): Flow<Resource<LastRaceDto>> = flow{
        try {
            emit(Resource.Loading(true))
            val winner = repository.getLastRaceCictuit()
            emit(Resource.Success(winner))
        }
        catch (e:HttpException){
            Log.d("tag", "error")
        }
        catch (e:IOException){
            Log.d("tag", "io error")
        }
    }
}