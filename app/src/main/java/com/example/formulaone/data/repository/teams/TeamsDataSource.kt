package com.example.formulaone.data.repository.teams

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.formulaoneapplicationn.common.Constants.API_KEY
import com.example.formulaoneapplicationn.common.Constants.INITIAL_LOAD_SIZE
import com.example.formulaoneapplicationn.common.Constants.NETWORK_PAGE_SIZE
import com.example.formulaoneapplicationn.common.Constants.NETWORK_PAGE_SIZE_TEAMS
import com.example.formulaoneapplicationn.common.Constants.STARTING_PAGE_INDEX
import com.example.formulaoneapplicationn.data.model.news.toArticleDomain
import com.example.formulaoneapplicationn.data.model.teams.ToTeamsDomain
import com.example.formulaoneapplicationn.data.services.NewsService
import com.example.formulaoneapplicationn.data.services.RaceService
import com.example.formulaoneapplicationn.domain.model.ArticleDomain
import com.example.formulaoneapplicationn.domain.model.TeamsDomain
import java.io.IOException
import java.util.Collections.max


class TeamsDataSource(private val api: RaceService) : PagingSource<Int, TeamsDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TeamsDomain> {

        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE_TEAMS) + 1 else INITIAL_LOAD_SIZE

        Log.d("parametrebi","page: $position \n")
        Log.d("parametrebi","params.key ${params.key} \n")
        Log.d("parametrebi","params loadsize ${params.loadSize} \n")

        return try {
            val response = api.getTeamsPAging(params.loadSize.toString(),offset.toString())
            val articles = response.body()!!.MRData.ConstructorTable.Constructors!!.map { it.ToTeamsDomain()}

            val nextKey = if (articles.isEmpty()) {
                if (position == 0) {
                    Log.d("parametrebi","sndjaasd")
                } else {
                    null
                }
            } else {
                position + 1
            }

            val previousKey = if (position == INITIAL_LOAD_SIZE) null else position - 1
            Log.d("parametrebi","nextKey: $nextKey \n \n")

            LoadResult.Page(
                data = articles,
                prevKey = previousKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: retrofit2.HttpException) {
            LoadResult.Error(exception)
        }catch (e:Exception){
            LoadResult.Error(e.cause!!)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, TeamsDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun ensureValidKey(key: Int) = kotlin.math.max(STARTING_PAGE_INDEX, key)
}