//package com.example.formulaone.data.repository
//
//import com.example.formulaone.data.model.links.toLinksDomain
//import com.example.formulaone.domain.model.LinksDomain
//import com.example.formulaone.domain.repository.LinksRepository
//import com.example.formulaoneapplicationn.common.Resource
//import com.example.formulaoneapplicationn.data.services.LinksService
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//class LinksRepositoryImpl @Inject constructor(
//    private val api: LinksService,
//) : LinksRepository {
//
//
//    override suspend fun getLinks(): Flow<Resource<List<LinksDomain>>> = flow {
//        try {
//            emit(Resource.Loading(true))
//            val response = api.getLinks()
//            if (response.isSuccessful) {
//                emit(Resource.Success(response.body()!!.articles!!.map { it!!.toLinksDomain() }))
//            }
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "unexpected"))
//        } catch (e: IOException) {
//            emit(Resource.Error(e.message.toString()))
//        }
//    }
//
//}
//
