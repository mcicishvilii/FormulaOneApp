package com.example.formulaone.ui.mainFragment

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.data.drivers.last_race.LastRaceDto
import com.example.formulaone.data.drivers.last_race.MRData
import com.example.formulaone.data.drivers.last_race.RaceTable
import com.example.formulaone.domain.use_case.CurrentDriversStandingsUseCase
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import com.example.formulaone.domain.use_case.last_race.GetLastRaceCircuitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase,
    private val getLastRaceCircuitUseCase: GetLastRaceCircuitUseCase

) : ViewModel() {
    private val _state = MutableStateFlow<Resource<UiModel>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    var apiCount = 0

    val uiModelHashMap = mutableMapOf<Int,String?>()

        fun getData(){

            viewModelScope.launch {

                getLastRaceCircuitUseCase().onEach { it
                    when (it){
                        is Resource.Success -> {
                            uiModelHashMap[1] = it.data.MRData.RaceTable.Races[0].raceName
                            apiCount += 1
                            submitState()
                        }
                        is Resource.Error -> {
                            uiModelHashMap[1] = null
                            apiCount += 1
                            submitState()

                        }
                        is Resource.Loading -> {
                            _state.value = Resource.Loading(true)
                        }
                    }
                }.launchIn(viewModelScope)

                currentDriversStandingsUseCase().onEach { result ->
                    when (result){
                        is Resource.Success -> {
                            uiModelHashMap[0] = result.data.MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver.givenName + " " +
                                    result.data.MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver.familyName
                            apiCount += 1
                            submitState()
                        }
                        is Resource.Error -> {
                            uiModelHashMap[0] = null
                            apiCount += 1
                            submitState()
                        }
                        is Resource.Loading -> {
                            _state.value = Resource.Loading(true)
                        }
                    }
                }.launchIn(viewModelScope)


            }

    }

    private fun submitState(){
        if(apiCount == 2){
            _state.value = Resource.Success(UiModel(uiModelHashMap[0],uiModelHashMap[1]))
        }
    }




//    fun getData(){
//        currentDriversStandingsUseCase().onEach { result ->
//            when (result){
//                is Resource.Success -> _driverState.value = Resource.Success(result.data)
//                is Resource.Error -> _driverState.value = Resource.Error("woops!")
//                is Resource.Loading -> _driverState.value = Resource.Loading(true)
//            }
//        }.launchIn(viewModelScope)
//
//        getLastRaceCircuitUseCase().onEach { result ->
//            when (result){
//                is Resource.Success -> _circuitState.value = Resource.Success(result.data)
//                is Resource.Error -> _circuitState.value = Resource.Error("woops!")
//                is Resource.Loading -> _circuitState.value = Resource.Loading(true)
//            }
//        }.launchIn(viewModelScope)
//    }
//



}