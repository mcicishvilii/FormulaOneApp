package com.example.formulaone.domain.use_case.news


import android.util.Log
import com.example.formulaone.Resource
import com.example.formulaone.data.remote.news.NewsDto
import com.example.formulaone.data.remote.news.new_api.F1NewsDto
import com.example.formulaone.domain.model.remote.ArticleDomain
import com.example.formulaone.domain.model.remote.TeamsDomain
import com.example.formulaone.domain.repository.local.TeamsRepositoryLocal
import com.example.formulaone.domain.repository.remote.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


// using to display all the teams in teams fragment
class NewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ArticleDomain>>> = flow{
        try {
            emit(Resource.Loading(true))
            val raceData = repository.getNews()
            emit(Resource.Success(raceData))
        }
        catch (e: HttpException){
            Log.d("tag", "error")
        }
        catch (e: IOException){
            Log.d("tag", "io error")
        }
    }
}