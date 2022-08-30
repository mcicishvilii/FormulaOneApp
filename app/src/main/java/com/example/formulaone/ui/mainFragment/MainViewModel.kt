package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.models.teamsResponse.Teams
import com.example.formulaone.network.RetrofitHelper
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
//    private val currentDriversLiveData = MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?>()
//
//
//    init {
//        viewModelScope.launch {
//            moviesLiveData.postValue(RetrofitHelper.constructorsService.getDriversList().MRData.ConstructorTable.Constructors)
//        }
//    }
//
//    fun getPopularMoviesLiveData(): MutableLiveData<List<Teams.MRdata.ConstructorsTable.Constructor>?> {
//        return moviesLiveData
//    }
}