package com.kelly.footballmatch.list.Event

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.FootballMatch
import com.kelly.footballmatch.model.Leagues
import com.kelly.footballmatch.model.TeamResponse
import com.kelly.footballmatch.model.repository.LocalRepositoryApi
import com.kelly.footballmatch.util.CoroutineContextProvider

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     val localRepositoryImpl: LocalRepositoryApi,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val contextMain: CoroutineContextProvider = CoroutineContextProvider()) {
    lateinit var event  :Event

    var menu = 1

    fun getLastMatch(leagueId: String?,leagueName: String?) {
        menu = 1
        view.showLoading()
        async(contextMain.main){

            var data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getLastMatch(leagueId)),
                        FootballMatch::class.java
                )
            }
            var Teamsdata = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getAllTeam(leagueName!!)),
                        TeamResponse::class.java
                )
            }
            for (part in data.await().events!!){
                var TeamHome = Teamsdata.await().teams.find{
                    it.strTeam == part.strHomeTeam
                }
                var TeamAway = Teamsdata.await().teams.find{
                    it.strTeam == part.strAwayTeam
                }
                part.strHomeBadge = TeamHome!!.strTeamBadge
                part.strAwayBadge = TeamAway!!.strTeamBadge
            }
                view.hideLoading()
                view.showLastMatch(data.await().events!!)
            }
    }
    fun getNextMatch(leagueId: String?,leagueName:String?) {
        menu = 2
        view.showLoading()
        async(contextMain.main) {

            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getUpcomingMatch(leagueId)),
                        FootballMatch::class.java
                )
            }
            var Teamsdata = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getAllTeam(leagueName!!)),
                        TeamResponse::class.java
                )
            }
            for (part in data.await().events!!){
                var TeamHome = Teamsdata.await().teams.find{
                    it.strTeam == part.strHomeTeam
                }
                var TeamAway = Teamsdata.await().teams.find{
                    it.strTeam == part.strAwayTeam
                }
                part.strHomeBadge = TeamHome!!.strTeamBadge
                part.strAwayBadge = TeamAway!!.strTeamBadge
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
            view.showAllLeague(data.await().leagues)
        }
    }
}