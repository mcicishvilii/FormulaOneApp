package com.example.formulaone.domain.use_case.schedule

import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.data.remote.drivers.last_race.LastRaceDto
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.repository.remote.LastRaceRepository
import com.example.formulaone.domain.repository.remote.RacesSheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


// using as the name suggests
class RaceScheduleUseCase @Inject constructor(
    private val repository: RacesSheduleRepository
) {
    operator fun invoke(): Flow<Resource<List<RaceScheduleDomain>>> = flow{
        try {
            emit(Resource.Loading(true))
            val raceData = repository.getRacesShceduleData()
            emit(Resource.Success(raceData))
        }
        catch (e:HttpException){
            Log.d("tag", "error")
        }
        catch (e:IOException){
            Log.d("tag", "io error")
        }
    }
}