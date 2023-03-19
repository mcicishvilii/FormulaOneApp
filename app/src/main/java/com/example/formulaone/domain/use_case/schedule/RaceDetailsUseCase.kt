package com.example.formulaone.domain.use_case.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.formulaoneapplicationn.domain.model.RaceDomain
import com.example.formulaone.domain.repository.RaceResultsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.common.utils.TimeFormaterIMPL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RaceDetailsUseCase @Inject constructor(
    private val repository: RaceResultsRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(): Flow<Resource<List<RaceDomain>>> = channelFlow {
        repository.GetRaceResultsRepository().collectLatest {
            when (it) {
                is Resource.Success -> {
                    val filteredList = it.data.filter {
                        val dateNow = TimeFormaterIMPL().formatCurrentTime()
                        val dateFromModel = it.date
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val date = LocalDate.parse(dateFromModel, formatter)
                        dateNow >= date
                    }
                    send(Resource.Success(filteredList))
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