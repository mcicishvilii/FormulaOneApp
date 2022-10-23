package com.example.formulaone.ui.mainFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.news.new_api.F1NewsDto
import com.example.formulaone.domain.model.remote.ArticleDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.use_case.last_race.GetLastRaceCircuitUseCase
import com.example.formulaone.domain.use_case.last_race.GetLastRaceWinnerUseCase
import com.example.formulaone.domain.use_case.news.NewsUseCase
import com.example.formulaone.domain.use_case.schedule.RaceScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRaceScheduleUseCase: RaceScheduleUseCase
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceScheduleDomain>>>(Resource.Loading(false))
    val state = _state1.asStateFlow()


    fun getSchedule() {
        getRaceScheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state1.value = Resource.Success(result.data)
                is Resource.Error -> _state1.value = Resource.Error("woops!")
                is Resource.Loading -> _state1.value = Resource.Loading(true)
            }
        }.launchIn(viewModelScope)
    }

}