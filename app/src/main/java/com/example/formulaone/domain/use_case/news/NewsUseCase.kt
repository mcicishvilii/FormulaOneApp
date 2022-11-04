package com.example.formulaone.domain.use_case.news


import android.util.Log
import com.example.formulaone.common.Resource
import com.example.formulaone.domain.model.ArticleDomain
import com.example.formulaone.domain.repository.NewsRepository
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