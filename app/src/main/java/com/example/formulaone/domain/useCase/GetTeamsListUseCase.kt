package com.example.formulaone.domain.useCase

import com.example.formulaone.Resource
import com.example.formulaone.domain.repository.Repository
import com.example.formulaone.models.teams.Teams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamsListUseCase @Inject constructor(
    private val repository: Repository
){
    operator fun invoke(): Flow<Resource<Teams>> = flow {
        try {
            emit(Resource.Loading(true))
            val teams = repository.getTeamsData()
            emit(Resource.Success(teams))
        }
        catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected"))
        }
        catch (e:IOException){
            emit(Resource.Error("couldn't reach server"))
        }
    }
}