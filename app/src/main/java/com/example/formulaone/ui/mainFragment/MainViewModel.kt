package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.useCase.GetTeamsListUseCase
import com.example.formulaone.domain.useCase.GetWinningDriverUseCase
import com.example.formulaone.models.drivers.plugin.PluginStandings
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.RetrofitHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWinningDriverUseCase: GetWinningDriverUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<PluginStandings>>(Resource.Loading(false))
    val state = _state.asStateFlow()





    init {
        getLastWinningDriver()
    }

    private fun getLastWinningDriver(){
        getWinningDriverUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }


}