package com.kelly.footballmatch.list.team.players

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.model.TeamPlayers
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