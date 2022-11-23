package com.example.formulaoneapplicationn.data.repository

import com.example.formulaoneapplicationn.data.model.news.toArticleDomain
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaone.domain.repository.NewsRepository
import com.example.formulaoneapplicationn.common.Resource
import com.example.formulaoneapplicationn.data.services.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsService,
) : NewsRepository {

    override suspend fun getNews(): Flow<Resource<List<ArticleDomain>>> = flow {
        try {
            emit(Resource.Loading(true))
            val response = api.getNews("formula one", "6ce7c585fe714572ad745ea44c378403")
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!.articles.map { it.toArticleDomain()}))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}
