package com.example.formulaone.ui.mainFragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.common.Resource
import com.example.formulaone.domain.model.RaceScheduleDomain
import com.example.formulaone.domain.use_case.last_race.GetLastRaceCircuitUseCase
import com.example.formulaone.domain.use_case.last_race.GetLastRaceWinnerUseCase
import com.example.formulaone.domain.use_case.schedule.RaceScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRaceScheduleUseCase: RaceScheduleUseCase,
    private val getLastRaceCircuitUseCase: GetLastRaceCircuitUseCase,
    private val getLastRaceWinnerUseCase: GetLastRaceWinnerUseCase,
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceScheduleDomain>>>(Resource.Loading(false))
    val state = _state1.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getSchedule() {
        getRaceScheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state1.value = Resource.Success(result.data)
                is Resource.Error -> _state1.value = Resource.Error("woops!")
                is Resource.Loading -> _state1.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

}