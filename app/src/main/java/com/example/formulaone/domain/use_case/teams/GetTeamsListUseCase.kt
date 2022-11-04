package com.example.formulaone.domain.use_case.teams

import com.example.formulaone.common.Resource
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.domain.model.TeamsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


// using to display all the teams in teams fragment
class GetTeamsListUseCase @Inject constructor(
    private val repository: TeamsRepository
){
    operator fun invoke(): Flow<Resource<List<TeamsDomain>>> = flow {
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