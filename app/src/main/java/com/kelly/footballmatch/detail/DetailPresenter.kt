package com.kelly.footballmatch.detail

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.list.DetailView
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.FootballMatch
import com.kelly.footballmatch.model.TeamResponse
import com.kelly.footballmatch.model.repository.LocalRepositoryApi
import com.kelly.footballmatch.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView.View,
                      val localRepositoryImpl: LocalRepositoryApi)
    : DetailView.Presenter {

    fun showDataTeam(event: Event?){
        view.showDataTeam(event)
    }

    fun goToDetailActivity(teamId: String?){
        val gson = Gson()
        val apiRepository = ApiRepository()
        async(context = CoroutineContextProvider().main) {

            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }
            view.goToDetailActivity(data.await().teams[0])

        }
    }
    override fun deleteMatch(id: String?) {
        localRepositoryImpl.deleteData(id)
    }

    override fun checkMatch(id: String?) {
        view.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun insertMatch(data: Event) {
        localRepositoryImpl.insertData(data)
    }
}