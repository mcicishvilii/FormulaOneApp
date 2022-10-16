package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.use_case.teams.DeleteAllUseCase
import com.example.formulaone.domain.use_case.teams.DeleteTeamUseCase
import com.example.formulaone.domain.use_case.teams.GetTeamsListUseCase
import com.example.formulaone.domain.use_case.teams.InsertTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
