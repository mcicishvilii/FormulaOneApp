package com.example.formulaone.ui.navMenuFragments.schedule.recent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.Resource
import com.example.formulaone.domain.model.remote.RaceDomain
import com.example.formulaone.domain.model.remote.RaceScheduleDomain
import com.example.formulaone.domain.use_case.schedule.RaceDetailsUseCase
import com.example.formulaone.domain.use_case.schedule.RaceScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class RecentRacesViewModel @Inject constructor(
    private val getRaceDetailsUseCase: RaceDetailsUseCase
) : ViewModel() {

    private val _state1 =
        MutableStateFlow<Resource<List<RaceDomain>>>(Resource.Loading(false))
    val state1 = _state1.asStateFlow()


    init {
        getDetails()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDetails() {
        getRaceDetailsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val filteredList = result.data.filter {
                        val time = Calendar.getInstance().time
                        val formatterCurrentTime = SimpleDateFormat("yyyy-MM-dd")
                        val formatterNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val currentTime = formatterCurrentTime.format(time)
                        val dateNow = LocalDate.parse(currentTime, formatterNow)

                        val dateFromModel = it.date
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val date = LocalDate.parse(dateFromModel, formatter)

                        dateNow >= date
                    }
                    _state1.value = Resource.Success(filteredList)
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
