package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.use_case.CurrentDriversStandingsUseCase
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import com.example.formulaone.domain.use_case.last_race.GetLastRaceCircuitUseCase
import com.example.formulaone.domain.use_case.last_race.GetLastRaceWinnerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase,
    private val getLastRaceCircuitUseCase: GetLastRaceCircuitUseCase,
    private val getLastRaceWinnerUseCase: GetLastRaceWinnerUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<DriverStandingsDto>>(Resource.Loading(false))
    val state = _state.asStateFlow()






    init {
        getLastWinningDriver()
    }

    private fun getLastWinningDriver(){
        currentDriversStandingsUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }


}