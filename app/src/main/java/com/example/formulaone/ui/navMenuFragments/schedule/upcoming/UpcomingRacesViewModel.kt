package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.use_case.schedule.RaceScheduleUseCase
import com.example.formulaone.domain.use_case.teams.DeleteAllUseCase
import com.example.formulaone.domain.use_case.teams.DeleteTeamUseCase
import com.example.formulaone.domain.use_case.teams.GetTeamsListUseCase
import com.example.formulaone.domain.use_case.teams.InsertTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class UpcomingRacesViewModel @Inject constructor(
    private val getRaceScheduleUseCase: RaceScheduleUseCase
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceScheduleDomain>>>(Resource.Loading(false))
    val state = _state1.asStateFlow()

    init {
        getSchedule()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSchedule() {
        getRaceScheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state1.value = Resource.Success(result.data)
                }
                is Resource.Error -> {
                    _state1.value = Resource.Error("woops!")
                }
                is Resource.Loading -> {
                    _state1.value = Resource.Loading(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
