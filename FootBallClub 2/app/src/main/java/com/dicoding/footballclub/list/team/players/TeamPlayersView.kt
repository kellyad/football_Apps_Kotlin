package com.dicoding.footballclub.list.team.players

import com.dicoding.footballclub.model.Player

interface TeamPlayersView {

    fun showLoading()
    fun hideLoading()

    fun showEmptyData()
    fun showTeamPlayer(data: List<Player>)

}