package com.dicoding.footballclub.list.team.players

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.list.team.TeamView
import com.dicoding.footballclub.model.TeamPlayers
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPlayersPresenter(private val view: TeamPlayersView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson)  {
    fun getAllPlayer(teamName: String = "") {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getAllPlayer(teamName)),
                    TeamPlayers::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamPlayer(data.player)
            }
        }
    }
}