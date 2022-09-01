package com.example.formulaone.ui.mainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.models.drivers.plugin.PluginStandings
import com.example.formulaone.network.RetrofitHelper
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val currentDriversLiveData =
        MutableLiveData<List<PluginStandings.MRDataX.StandingsTableX.StandingsListsX.DriverStanding.DriverX>?>()

    init {
        viewModelScope.launch {
            currentDriversLiveData.postValue(listOf(RetrofitHelper.driversService.getCurrentStandings().MRData.StandingsTable.StandingsLists[0].DriverStandings[0].Driver))
        }
    }

    fun getCurrentStandingsLivedata(): MutableLiveData<List<PluginStandings.MRDataX.StandingsTableX.StandingsListsX.DriverStanding.DriverX>?> {
        return currentDriversLiveData
    }
}