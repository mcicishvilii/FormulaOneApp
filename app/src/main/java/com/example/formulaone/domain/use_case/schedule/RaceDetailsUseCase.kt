package com.example.formulaone.domain.use_case.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.formulaone.common.Resource
import com.example.formulaone.common.utils.TimeFormaterIMPL
import com.example.formulaone.domain.model.RaceDomain
import com.example.formulaone.domain.repository.RaceResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class RaceDetailsUseCase @Inject constructor(
    private val repository: RaceResultsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): Flow<Resource<List<RaceDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val raceData = repository.GetRaceResultsRepository()

            val filteredList = raceData.filter {
                val dateNow = TimeFormaterIMPL().formatCurrentTime()

                val dateFromModel = it.date
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(dateFromModel, formatter)

                dateNow >= date
            }

            emit(Resource.Success(filteredList))


        } catch (e: HttpException) {
            Log.d("tag", "error")
        } catch (e: IOException) {
            Log.d("tag", "io error")
        }
    }
}