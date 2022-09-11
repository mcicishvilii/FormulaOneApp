package com.example.formulaone.domain.useCase

import com.example.formulaone.Resource
import com.example.formulaone.domain.repository.LastWinningDriverRepository
import com.example.formulaone.domain.repository.TeamsRepository
import com.example.formulaone.models.drivers.plugin.PluginStandings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWinningDriverUseCase @Inject constructor(
    private val repository: LastWinningDriverRepository
){
    operator fun invoke(): Flow<Resource<PluginStandings>> = flow {
        try {
            emit(Resource.Loading(true))
            val winningDriver = repository.getLastWinningDriver()
            emit(Resource.Success(winningDriver))
        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected"))
        }
        catch (e: IOException){
            emit(Resource.Error("couldn't reach server"))
        }
    }
}