package com.example.formulaone.ui.navMenuFragments.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.domain.model.LinksDomain
import com.example.formulaone.domain.use_case.links.LinksUseCase
import com.example.formulaoneapplicationn.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val linksUseCase: LinksUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<LinksDomain>>>(Resource.Loading(false))
    val state = _state.asStateFlow()

    fun getTeams() {
        viewModelScope.launch {
            linksUseCase().onEach { news ->
                when (news) {
                    is Resource.Success -> {
                        _state.value = Resource.Success(news.data)
                    }
                    is Resource.Error -> {
                        _state.value = Resource.Error("woops!")
                    }
                    is Resource.Loading -> {
                        _state.value = Resource.Loading(true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}