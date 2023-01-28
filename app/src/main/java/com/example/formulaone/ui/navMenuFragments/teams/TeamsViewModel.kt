package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.formulaone.data.repository.teams.TeamsRepositoryImpl
import com.example.formulaone.domain.use_case.teams.*
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.model.teams.Teams
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsRepositoryImpl: TeamsRepositoryImpl,
    private val insertTeamUseCase: InsertTeamUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<TeamsDomain>>(PagingData.empty())
    val state = _state.asStateFlow()

//    private var currentResult: Flow<PagingData<TeamsDomain>>? = null



//    suspend fun getTeams(): Flow<PagingData<TeamsDomain>> {
//        val newResult: Flow<PagingData<TeamsDomain>> =
//            teamsRepositoryImpl.getTeamsData().cachedIn(viewModelScope)
//        currentResult = newResult
//        return newResult
//    }

    fun getTeams(){
        viewModelScope.launch {
            teamsRepositoryImpl.getTeamsData().cachedIn(viewModelScope).collectLatest {
                _state.value = it
            }
        }
    }

//    suspend fun searchTeams(queryString: String): Flow<PagingData<TeamsDomain>> =
//        teamsRepositoryImpl.getTeamsData(queryString)




    fun insertTeam(team: TeamsDomain) {
        CoroutineScope(Dispatchers.IO).launch {
            insertTeamUseCase(team)
        }
    }
}









