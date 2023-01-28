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

        // გვერდის ნომერი - თუ params.key ნალია დააბრუნე INITIAL_LOAD_SIZE რაც არის 1 (კონსტანტებში)
        val position = params.key ?: INITIAL_LOAD_SIZE

        // ერთ გვერდზე ჩასატვირთი აითემების რაოდენობა
        // offset უდრის - თუ (პარამს.ქი ანუ იგივე ახლანდელი გვერდი არაა ნალი, ანუ პირველი ან ნებისმიერი გვერდი თუ ჩაიტვირთა)
        // მაშინ {offset გახდეს ამჟამინდელი გვერდი გამრავლებული პეიჯსაიზზე (კონსტანტებში) }
        // მაგრამ თუ (პარამს.ქი ნალია, ანუ გვერდი არ ჩატვირთულა,) მაშინ
        // {ოფსეტი იყოს პირვალნდელი მონაცემი ანუ ინიტიალ ლოად საიზ}

        val offset = if (params.key != null) {
            (position * NETWORK_PAGE_SIZE_TEAMS)
        } else{
            INITIAL_LOAD_SIZE
        }

        Log.d("parametrebi","position: $position \n")
        Log.d("parametrebi","params.key ${params.key} \n")
        Log.d("parametrebi","params loadsize ${params.loadSize} \n")
        Log.d("parametrebi","offset $offset \n")

        return try {
            // მოგვაქ ეიპიაი ქოლი სადაც გადავცემთ პირველ პოზიციად 1) ლოადსაიზსს ანუ სიტყვაც გვეუბნება - რამდენი უნდა ჩაიტვირთოს ერთ გვერდზე
            //                                                    2) და იმ ოფსეტს - რომელიც გვერდის მიხედვით იცვლება.
            // ანუ თუ პირველი გვერდია მოაქვს 1 იმიტორო პრიველ გვერდს ოფსეტი არ ჭირდება.
            // თუ მეორე გვერდია მოაქვს 2 * ლოადსაიზზე ანუ ათზე = 20
            // თუ მესამე გვერდია 3 * 10 = 30
            // ანუ პირველ გვერდზე იტვირთება პირველი 30 გუნდი, მერე იტვირთება 30-40 გუნდი, მერე 40-50 და ასე შემდეგ.

            val response = api.getTeamsPAging(params.loadSize.toString(),offset.toString())
            // აქ მიღებულ მონაცემებს ვმაპავთ დომეინ დატა კლასში და შედეგად ვიღებთ მონაცემთა სიას.
            val articles = response.body()!!.MRData.ConstructorTable.Constructors!!.map { it.ToTeamsDomain()}

            // აქ ვსაზღვრავთ როგორ მიხვდეს პეიჯინგი რო შემდეგი გვერდია ჩასატვირთი - ანუ ნექსთ ქი

            // ჯერ ვამოწმებთ სია ცარიელია თუ არა. თუ ცარიელია ვაბრუნებთ რამე ერორის კოდს.
            // თუ არ არის ცარიელი, position-ს ანუ, ამ კონკრეტულ მომენტში რა გვერდზეც ვართ იმ რიცხვს ვუმატებთ 1-ს ანუ ვაკეთებთ შემდეგ გვერდს
            val nextKey = if (articles.isEmpty()) {
                if (position == 0) {
                    Log.d("parametrebi","რამე ერორ ჰენდლიგნი")
                } else {
                    null
                }
            } else {
                position + 1
            }

            // ეს არის წინა გვერდი. შემდეგი გვერდის გასაკეთებლად თუ არსებულ გვერდს ანუ position-ს ვუმატებთ ერთს, აქ პირიქით ვაკლებთ.
            // თან ვამოწმებთ, თუ პოზიცია, ანუ გვერდი ამწამს რაზეც ვართ არის 1(ინიტიალ_ლოად_საიზ) მაშინ პრევიუს ქი ანუ წინა გვერდი განალდეს იმიტორო რეალურად არ არსებობს წინა გვერდი
            // და თუ რომელიმე სხვა გვერდზე ვართ, მაგალითად მესამეზე მაშინ -1 ანუ გადავიდეთ მეორე გვერდზე.
            val previousKey = if (position == INITIAL_LOAD_SIZE) null else position - 1
            Log.d("parametrebi","nextKey: $nextKey \n")
            Log.d("parametrebi","prevKey: $previousKey \n \n")

            // ამით უბრალოდ ვატანთ ჩვენს დამუშავებულ ინფორმაციას პეიჯინგს და ვაგზავნით რეპოიმპლ-ში
            LoadResult.Page(
                data = articles, // მონაცემები რაც წამოვიღეთ აპი დან
                prevKey = previousKey,// წინა გვერდი
                nextKey = nextKey // შემდეგი გვერდი
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
}



// როგორც კი სია რაზეცაა იმ ფრაგმენტს გავხსნით ეგრევე ლოგქათში მოდის ეს მონაცემები:

// params.key null - თავიდან ეს ნალია სულ
// position: 1 - რომელი გვერდია ახლა
// params loadsize 30 - რამდენი გუნდი ჩაიტვირთოს ერთ გვერდზე (ეს პირველად არის 3 * NETWORK_PAGE_SIZE_TEAMS და მერე ხდება NETWORK_PAGE_SIZE_TEAMS)
// offset 1 - პირველ გვერდს ოფსეტი(იგივე რეინჯი) 1 აქვს, იმიტორო პირველ გვერდს არ ჭირდება რეალურად რეინჯი რადგან ეუბნები რო 30 ჩაგიტვირთოს მხოლოდ.
// nextKey: 2 - შემდეგი გვერდის მანიშნებელი
// prevKey: null - წინა გვერდის მანიშნებელი (პირველ გვერდზე ეს არის სულ ნალი)

// როგორც კი ოდნავ ჩასქროლავ ლოგქათში ეს ინფო  მოდის:

// params.key 2 - გაუტოლდა იმ გვერდს რომელი გვერდიცაა ეხლა. (აღარაა ნალი იმიტორო დაიწყო ათვლა)
// position: 2 - რომელი გვერდია ახლა
// params loadsize 10 - აღარაა 30 და არის 10 ანუ NETWORK_PAGE_SIZE_TEAMS.
// offset 20 -  ოფსეტი უკვე გახდა 20 ანუ 30 საწყისს + 20 შემდეგ აითემი ჩაიტვირთა ჯამში. ანუ ახლა ვდგავართ 31-ე გუნდიდან 40 გუნდის ჩათვლით
// nextKey: 3 - ეს უკვე 3 გახდა იმიტორო ახლა რაზეც ვდგავართ მეორე გვერდია და შესაბამისად 2+1
// prevKey: 1 - წინა გვერდი გახდა 1 იმიტორო ეხლა ვართ გვერდ 2-ზე შესაბამისად 2-1