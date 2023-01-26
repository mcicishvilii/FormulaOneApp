package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.formulaone.data.repository.teams.TeamsRepositoryImpl
import com.example.formulaone.domain.use_case.teams.*
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsRepositoryImpl: TeamsRepositoryImpl,
    private val insertTeamUseCase: InsertTeamUseCase,
) : ViewModel() {

    private var filteredList = listOf<TeamsDomain>()

    private val _state = MutableStateFlow<Resource<List<TeamsDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    private var currentResult: Flow<PagingData<TeamsDomain>>? = null

    init {
        viewModelScope.launch {
            getTeams()
        }
    }

    suspend fun getTeams():Flow<PagingData<TeamsDomain>> {
        val newResult: Flow<PagingData<TeamsDomain>> =
            teamsRepositoryImpl.getTeamsData().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

    fun insertTeam(team: TeamsDomain){
        CoroutineScope(Dispatchers.IO).launch {
            insertTeamUseCase(team)
        }
    }

//    fun search(query:String) {
//        val searchedList = filteredList.filter {
//            it.nationality.toString().lowercase().contains(query.lowercase())
//        }
//        _state.value = Resource.Success(searchedList)
//    }
}



