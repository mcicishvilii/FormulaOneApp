package com.example.formulaone.domain.use_case.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import com.example.formulaone.domain.repository.RacesSheduleRepository
import com.example.formulaone.common.Resource
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject


class RaceScheduleUseCase @Inject constructor(
    private val repository: RacesSheduleRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): Flow<Resource<List<RaceScheduleDomain>>> = channelFlow {
        repository.getRacesShceduleData().collectLatest {
            when (it) {
                is Resource.Success -> {
                    send(Resource.Success(it.data))
                }
                is Resource.Error -> {
                    send(Resource.Error(it.error))
                }
                is Resource.Loading -> {
                    send(Resource.Loading(it.loading))
                }
            }
        }
    }
}


//                    val filteredList = it.data.filter {
//                        val dateNow = TimeFormaterIMPL().formatCurrentTime()
//                        val dateFromModel = it.date
//                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//                        val date = LocalDate.parse(dateFromModel, formatter)
//                        dateNow >= date
//                    }