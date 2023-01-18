package com.example.formulaone.ui.navMenuFragments.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.formulaone.data.model.links.Article
import com.example.formulaone.data.repository.news.NewsRepositoryImpl
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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


