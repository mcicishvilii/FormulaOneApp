package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.TeamsState
import com.example.formulaone.domain.repository.Repository
import com.example.formulaone.domain.useCase.GetTeamsListUseCase
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.RetrofitHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsListUseCase: GetTeamsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<Teams>>(Resource.Loading(false))
    val state = _state.asStateFlow()


    init {
        getTeams()
    }
    private fun getTeams(){
        getTeamsListUseCase().onEach { result ->
            when (result){
                is Resource.Success -> _state.value = Resource.Success(result.data)
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }


}





















// livedatati musha kodi.
//    private val teamsLiveData = MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?>()
//
//    init {
//        viewModelScope.launch {
//            teamsLiveData.postValue(RetrofitHelper.constructorsService.getDriversList().MRData.ConstructorTable.Constructors)
//        }
//    }
//
//    fun getPopularMoviesLiveData(): MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?> {
//        return teamsLiveData
//    }
