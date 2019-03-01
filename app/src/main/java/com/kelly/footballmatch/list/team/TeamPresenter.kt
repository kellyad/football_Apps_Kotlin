package com.kelly.footballmatch.list.team

import android.util.Log
import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.model.Leagues
import com.kelly.footballmatch.model.Team
import com.kelly.footballmatch.model.TeamResponse
import com.kelly.footballmatch.model.repository.LocalRepositoryApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(private val view: TeamView,
                    val localRepositoryImpl: LocalRepositoryApi,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getAllLeague() {
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getAllLeague()),
                    Leagues::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueList(data)
            }
        }
    }

    fun getAllTeam(leagueName: String = "") {
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getAllTeam(leagueName)),
                    TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()

                try {
                    view.showTeamList(data.teams)
                } catch (e: Exception) {
                    view.showEmptyData()
                }
            }
        }
    }

    fun getSearchedTeam(teamName: String = "") {
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getSearchedTeam(teamName)),
                    TeamResponse::class.java
            )
            Log.d("cek", data.toString())
            uiThread {
                view.hideLoading()

                try {
                    view.showTeamList(data.teams)
                } catch (e: Exception) {
                    view.showEmptyData()
                }
            }
        }
    }

    fun getFavoriteMatch() {
        view.showLoading()
        doAsync {
            val favList = localRepositoryImpl.getTeamFromDb()
            var teamList: MutableList<Team> = mutableListOf()
            teamList.addAll(favList)
            uiThread {
                view.hideLoading()
                view.showFavoriteTeamList(teamList)
            }
        }

    }

}