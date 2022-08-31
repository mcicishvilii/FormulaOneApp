package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.network.RetrofitHelper
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val currentDriversLiveData =
        MutableLiveData<String>()

    init {
        viewModelScope.launch {
            currentDriversLiveData.postValue(RetrofitHelper.driversService.getCurrentStandings().driver.toString())
        }
    }

    fun getCurrentStandingsLivedata(): MutableLiveData<String> {
        return currentDriversLiveData
    }
}