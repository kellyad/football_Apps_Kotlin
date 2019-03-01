package com.kelly.footballmatch.search.match

import android.util.Log
import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.model.FootballMatchSearch
import com.kelly.footballmatch.model.TeamResponse
import com.kelly.footballmatch.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class FootBallMatchSearchPresenter(private val view: FootBallMatchSearchView,
                                   private val apiRepository: ApiRepository,
                                   private val gson: Gson)  {

    fun getFootBallMatchesSearch(eventName: String = "") {
        view.showLoading()
        async(context = CoroutineContextProvider().main) {
            var data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getFootBallMatchesSearch(eventName)),
                        FootballMatchSearch::class.java
                )
            }.await()

            try {
                for (part in data.event!!) {
                    async(CoroutineContextProvider().main,CoroutineStart.LAZY) {
                        if(!part.idHomeTeam.equals(null)) {
                            var TeamHome = bg {
                                gson.fromJson(apiRepository
                                        .doRequest(TheSportsDBApi.getTeamDetail(part.idHomeTeam!!)),
                                        TeamResponse::class.java
                                )
                            }.await()

                            if (!TeamHome.teams[0].equals(null)) {
                                part.strHomeBadge = TeamHome.teams[0].strTeamBadge
                            }
                        }

                        if(!part.idAwayTeam.equals(null)) {
                            var TeamAway = bg {
                                gson.fromJson(apiRepository
                                        .doRequest(TheSportsDBApi.getTeamDetail(part.idAwayTeam!!)),
                                        TeamResponse::class.java
                                )
                            }.await()
                            if (!TeamAway.teams[0].equals(null)) {
                                part.strAwayBadge = TeamAway.teams[0].strTeamBadge
                            }
                        }
                    }.await()
                }

                view.hideLoading()
                view.showSearchedEvents(data.event!!)
            } catch (e: Exception) {
                view.hideLoading()
                view.showSearchedEvents(data.event!!)
            }

        }
    }
}