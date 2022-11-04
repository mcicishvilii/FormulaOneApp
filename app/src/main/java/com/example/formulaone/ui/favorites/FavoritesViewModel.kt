package com.example.formulaone.ui.favorites

import androidx.lifecycle.ViewModel
import com.example.formulaone.domain.model.TeamsDomain
import com.example.formulaone.domain.use_case.favs.GetFavTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavTeamsUseCase: GetFavTeamsUseCase
) : ViewModel() {


    suspend fun getTeam(): Flow<List<TeamsDomain>> {
        return getFavTeamsUseCase()
    }


}