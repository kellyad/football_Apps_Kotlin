package com.dicoding.footballclub.list.team

import com.dicoding.footballclub.model.Leagues
import com.dicoding.footballclub.model.Team

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showLeagueList(data: Leagues)
    fun showTeamList(data: List<Team>)
    fun showFavoriteTeamList(data: List<Team>)
}