package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.data.remote.teams.Teams
import com.example.formulaone.domain.use_case.teams.*
import com.example.formulaone.domain.model.remote.TeamsDomain
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
//    private val getTeamsFromDBUseCase: GetTeamsFromDBUseCase,
    private val deleteUseCase: DeleteTeamUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<TeamsDomain>>>(Resource.Loading(false))
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

    fun insertTeam(team: TeamsDomain){
        CoroutineScope(Dispatchers.IO).launch {
            insertTeamUseCase(team)
        }
    }

}



