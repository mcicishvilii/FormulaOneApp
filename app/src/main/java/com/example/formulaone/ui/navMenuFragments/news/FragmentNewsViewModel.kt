package com.example.formulaone.ui.navMenuFragments.news

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.formulaone.data.repository.news.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FragmentNewsViewModel @Inject constructor(
    private val newsRepo: NewsRepositoryImpl
) : ViewModel() {

    private val _currentQuery = MutableStateFlow(DEFAULT_QUERY)
    val currentQuery = _currentQuery.asStateFlow()

    val news = currentQuery.flatMapLatest {query ->
        newsRepo.getNews(query).cachedIn(viewModelScope)
    }

    fun searchNews(query:String){
        _currentQuery.value = query
    }
    companion object {
        private const val DEFAULT_QUERY = "Autosport"
    }
}


