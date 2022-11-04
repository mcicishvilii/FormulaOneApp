package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.common.Resource
import com.example.formulaone.domain.use_case.teams.*
import com.example.formulaone.domain.model.TeamsDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsListUseCase: GetTeamsListUseCase,
    private val insertTeamUseCase: InsertTeamUseCase,
) : ViewModel() {

    private var filteredList = listOf<TeamsDomain>()

    private val _state = MutableStateFlow<Resource<List<TeamsDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    init {
        getTeams()
    }

    private fun getTeams(){
        getTeamsListUseCase().onEach { result ->
            when (result){
                is Resource.Success -> {
                    filteredList = result.data
                    _state.value = Resource.Success(result.data)
                }
                is Resource.Error -> _state.value = Resource.Error("woops!")
                is Resource.Loading -> _state.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

    fun insertTeam(team: TeamsDomain){
        CoroutineScope(Dispatchers.IO).launch {
            insertTeamUseCase(team)
        }
    }

    fun searh(query:String) {
        val searchedList = filteredList.filter {
            it.nationality.toString().lowercase().contains(query.lowercase())
        }
        _state.value = Resource.Success(searchedList)
    }
}



