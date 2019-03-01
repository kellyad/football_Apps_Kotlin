package com.dicoding.footballclub.detail

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.list.DetailView
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.TeamResponse
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.experimental.async

class DetailPresenter(private val view: DetailView.View,
                     private val apiRepository: ApiRepository,
                      val localRepositoryImpl: LocalRepositoryImpl,
                     private val gson: Gson,
                      private val contextDetail: CoroutineContextProvider = CoroutineContextProvider()) : DetailView.Presenter {
    override fun deleteMatch(id: String?) {
        localRepositoryImpl.deleteData(id)
    }

    override fun checkMatch(id: String?) {
        view.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun insertMatch(data: Event) {
        localRepositoryImpl.insertData(data)
    }

    fun showDataTeam(event: Event?){
        view.showDataTeam(event)
    }

    fun getTeamsBadgeHome(teamId: String?) {
        //view.showLoading()
        async(contextDetail.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }
                    //                view.hideLoading()
                    view.displayTeamBadgeHome(data.await().teams!!)
        }
    }
    fun getTeamsBadgeAway(teamId: String?) {
        //view.showLoading()
        async(contextDetail.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }

                    //                view.hideLoading()
                    view.displayTeamBadgeAway(data.await().teams!!)

        }
    }
}