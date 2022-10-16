package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.use_case.last_race.GetLastRaceCircuitUseCase
import com.example.formulaone.domain.use_case.last_race.GetLastRaceWinnerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastRaceWinnerUseCase: GetLastRaceWinnerUseCase,
    private val getLastRaceCircuitUseCase: GetLastRaceCircuitUseCase

) : ViewModel() {
    private val _state = MutableStateFlow<Resource<UiModel>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    var apiCount = 0

    val uiModelHashMap = mutableMapOf<Int,String?>()

        fun getData(){

            viewModelScope.launch {

                getLastRaceCircuitUseCase().onEach { circuit ->
                    when (circuit){
                        is Resource.Success -> {
                            uiModelHashMap[1] = circuit.data.MRData.RaceTable.Races[0].raceName
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

                getLastRaceWinnerUseCase().onEach { winner ->
                    when (winner){
                        is Resource.Success -> {
                            uiModelHashMap[0] = winner.data.MRData.RaceTable.Races[0].Results[0].Driver.givenName + " " +
                                    winner.data.MRData.RaceTable.Races[0].Results[0].Driver.familyName
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
}