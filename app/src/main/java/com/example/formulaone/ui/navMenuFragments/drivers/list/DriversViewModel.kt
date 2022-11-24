package com.example.formulaone.ui.navMenuFragments.drivers.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.domain.model.QualiDomain
import com.example.formulaone.domain.use_case.QualiUseCase
import com.example.formulaoneapplicationn.domain.use_case.drivers.CurrentDriversStandingsUseCase
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(
    private val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase,
    private val qualiUseCase: QualiUseCase
) : ViewModel() {

    private val qualificationMap = mutableMapOf<String,Int>()
    private val listDriversQuali = mutableListOf<String>()

    private val _state = MutableStateFlow<Resource<List<DriverStandingDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    private val _qualiState = MutableStateFlow<Resource<List<QualiDomain>>>(Resource.Loading(false))
    val qualiState = _qualiState.asStateFlow()


    fun getDrivers(){
        currentDriversStandingsUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

//    fun getQuali(){
//        qualiUseCase().onEach { result ->
//            when (result){
//                is Resource.Success -> _qualiState.value = Resource.Success(result.data)
//                is Resource.Error -> _qualiState.value = Resource.Error("woops!")
//                is Resource.Loading -> _qualiState.value = Resource.Loading(true)
//            }
//        }.launchIn(viewModelScope)
//    }



}

