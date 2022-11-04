package com.example.formulaone.ui.navMenuFragments.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formulaone.common.Resource
import com.example.formulaone.domain.model.ArticleDomain
import com.example.formulaone.domain.use_case.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentNewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsState = MutableStateFlow<Resource<List<ArticleDomain>>>(Resource.Loading(false))
    val newsState = _newsState.asStateFlow()

    fun getNews() {
        viewModelScope.launch {
            newsUseCase().onEach { news ->
                when (news) {
                    is Resource.Success -> _newsState.value = Resource.Success(news.data)
                    is Resource.Error -> _newsState.value = Resource.Error("woops!")
                    is Resource.Loading -> _newsState.value = Resource.Loading(true)

                }
            }.launchIn(viewModelScope)

        }
    }

}


