package com.example.formulaone.ui.navMenuFragments.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.models.teams.Teams
import com.example.formulaone.network.RetrofitHelper
import kotlinx.coroutines.launch

class TeamsViewModel : ViewModel() {
    private val moviesLiveData = MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?>()


    init {
        viewModelScope.launch {
            moviesLiveData.postValue(RetrofitHelper.constructorsService.getDriversList().MRData.ConstructorTable.Constructors)
        }
    }

    fun getPopularMoviesLiveData(): MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?> {
        return moviesLiveData
    }
}