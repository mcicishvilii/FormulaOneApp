package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.domain.use_case.schedule.RaceScheduleUseCase
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class UpcomingRacesViewModel @Inject constructor(
    private val getRaceScheduleUseCase: RaceScheduleUseCase
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceScheduleDomain>>>(Resource.Loading(false))
    val state = _state1.asStateFlow()

    init {
        getSchedule()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSchedule() {
        getRaceScheduleUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state1.value = Resource.Success(result.data)
                }
                is Resource.Error -> {
                    _state1.value = Resource.Error("woops!")
                }
                is Resource.Loading -> {
                    _state1.value = Resource.Loading(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
