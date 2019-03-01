package com.dicoding.footballclub.list.team

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.model.Leagues
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.TeamResponse
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(private val view: TeamView,
                    val localRepositoryImpl: LocalRepositoryImpl,
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