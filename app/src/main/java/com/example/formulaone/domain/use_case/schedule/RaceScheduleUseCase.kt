package com.example.formulaone.domain.use_case.schedule

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.formulaone.common.Resource
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.repository.remote.RacesSheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


// using as the name suggests
class RaceScheduleUseCase @Inject constructor(
    private val repository: RacesSheduleRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): Flow<Resource<List<RaceScheduleDomain>>> = flow{
        try {
            emit(Resource.Loading(true))
            val raceData = repository.getRacesShceduleData()


            val filteredList = raceData.filter {
                val time = Calendar.getInstance().time
                val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
                val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val currentTime = formatterCurrentTime.format(time)
                val dateNow = LocalDate.parse(currentTime, formatterNow)

                val dateFromModel = it.date
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(dateFromModel, formatter)

                dateNow <= date

            }
            emit(Resource.Success(filteredList))
        }
        catch (e:HttpException){
            Log.d("tag", "error")
        }
        catch (e:IOException){
            Log.d("tag", "io error")
        }
    }
}