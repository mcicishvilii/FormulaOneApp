package com.example.formulaone.ui.navMenuFragments.drivers.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaoneapplicationn.domain.use_case.drivers.CurrentDriversStandingsUseCase
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.DriverStandingDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriverDetailsViewModel @Inject constructor(
    private val currentDriversStandingsUseCase: CurrentDriversStandingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<DriverStandingDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()


    fun getDrivers(){
        currentDriversStandingsUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }
}