package com.example.formulaone.ui.mainFragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.domain.use_case.last_race.GetLastRaceInformationUseCase
import com.example.formulaone.domain.use_case.weather.GetWeatherUseCase
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.DailyDomain
import com.example.formulaoneapplicationn.domain.model.LastRaceInfoDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastRaceInformationUseCase: GetLastRaceInformationUseCase,
    private val weatherUseCase: GetWeatherUseCase

) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<LastRaceInfoDomain>>>(Resource.Loading(false))
    val state = _state1.asStateFlow()


    private val _weatherState =
        MutableStateFlow<Resource<DailyDomain>>(Resource.Loading(false))
    val weatherState = _weatherState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getRacing() {
        viewModelScope.launch {
            getLastRaceInformationUseCase().onEach { info ->
                when (info) {
                    is Resource.Success -> _state1.value = Resource.Success(info.data)
                    is Resource.Error -> _state1.value = Resource.Error("woops!")
                    is Resource.Loading -> _state1.value = Resource.Loading(true)

                }
            }.launchIn(viewModelScope)
        }
    }


    fun getWeather(lat:Double,long:Double){
        weatherUseCase(lat,long).onEach { result ->
            when (result) {
                is Resource.Success -> _weatherState.value = Resource.Success(result.data)
                is Resource.Error -> _weatherState.value = Resource.Error("woops!")
                is Resource.Loading -> _weatherState.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }





}