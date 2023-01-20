package com.example.formulaone.ui.navMenuFragments.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.formulaone.data.repository.news.NewsRepositoryImpl
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FragmentNewsViewModel @Inject constructor(
    private val newsRepo: NewsRepositoryImpl
) : ViewModel() {

    private var currentResult: Flow<PagingData<ArticleDomain>>? = null

    suspend fun searchPlayers(): Flow<PagingData<ArticleDomain>> {
        val newResult: Flow<PagingData<ArticleDomain>> =
            newsRepo.getNews().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

}


