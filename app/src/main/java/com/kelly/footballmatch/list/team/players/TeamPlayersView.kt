package com.kelly.footballmatch.list.team.players

import com.kelly.footballmatch.model.Player

interface TeamPlayersView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showTeamPlayer(data: List<Player>)

}