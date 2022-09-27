package com.example.formulaone.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.use_case.favs.GetFavTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavTeamsUseCase: GetFavTeamsUseCase
) : ViewModel() {


    suspend fun getTeam(): Flow<List<TeamsDomain>> {
        return getFavTeamsUseCase()
    }


}