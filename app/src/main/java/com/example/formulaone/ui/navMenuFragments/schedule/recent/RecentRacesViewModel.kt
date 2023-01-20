package com.example.formulaone.ui.navMenuFragments.schedule.recent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaoneapplicationn.domain.use_case.schedule.RaceDetailsUseCase
import com.example.formulaone.common.Resource
import com.example.formulaoneapplicationn.domain.model.RaceDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class RecentRacesViewModel @Inject constructor(
    private val getRaceDetailsUseCase: RaceDetailsUseCase
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceDomain>>>(Resource.Loading(false))
    val state1 = _state1.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDetails() {
        getRaceDetailsUseCase().onEach { result ->
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
