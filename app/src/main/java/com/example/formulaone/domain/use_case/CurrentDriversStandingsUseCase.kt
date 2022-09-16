package com.example.formulaone.domain.use_case

import com.example.formulaone.Resource
import com.example.formulaone.domain.repository.CurrentDriversStandingsRepository
import com.example.formulaone.data.drivers.plugin.DriverStandingsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrentDriversStandingsUseCase @Inject constructor(
    private val repository: CurrentDriversStandingsRepository
){
    operator fun invoke(): Flow<Resource<DriverStandingsDto>> = flow {
        try {
            emit(Resource.Loading(true))
            val winningDriver = repository.getCurrentDriversStanding()
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