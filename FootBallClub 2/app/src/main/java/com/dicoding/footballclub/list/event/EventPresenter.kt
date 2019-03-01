package com.dicoding.footballclub.list.Event

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.FootballMatch
import com.dicoding.footballclub.model.Leagues
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     val localRepositoryImpl: LocalRepositoryImpl,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val contextMain: CoroutineContextProvider = CoroutineContextProvider()) {
    lateinit var event:Event

    var menu = 1

    fun getLastMatch(leagueId: String?) {
        menu = 1
        view.showLoading()
        async(contextMain.main){

            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getLastMatch(leagueId)),
                        FootballMatch::class.java
                )
            }
                view.hideLoading()
                view.showLastMatch(data.await().events!!)
            }
    }
    fun getNextMatch(leagueId: String?) {
        menu = 2
        view.showLoading()
        async(contextMain.main) {

            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getUpcomingMatch(leagueId)),
                        FootballMatch::class.java
                )
            }

                view.hideLoading()
                view.showNextMatch(data.await().events!!)
        }
    }
    fun getFavoriteMatch() {
        menu = 3
        view.showLoading()
        val favList = localRepositoryImpl.getMatchFromDb()
        var eventList: MutableList<Event> = mutableListOf()
        eventList.addAll(favList)
        view.hideLoading()
        view.showFavoriteMatch(eventList)

    }
    fun getAllLeague(){
        view.showLoading()
        async(contextMain.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getAllLeague()),
                        Leagues::class.java
                )
            }

            view.hideLoading()
            view.showAllLeague(data.await().leagues!!)
        }
    }
}