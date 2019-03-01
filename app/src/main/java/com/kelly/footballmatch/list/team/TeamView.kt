package com.kelly.footballmatch.list.team

import com.kelly.footballmatch.model.Leagues
import com.kelly.footballmatch.model.Team

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showTeamList(data: List<Team>)
    fun showLeagueList(data: Leagues)
    fun showFavoriteTeamList(data: List<Team>)
}