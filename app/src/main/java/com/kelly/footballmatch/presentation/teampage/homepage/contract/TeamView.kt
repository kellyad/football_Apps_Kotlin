package com.kelly.footballmatch.presentation.teampage.homepage.contract

import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.teams.Team

interface TeamView {
    interface View{
        fun initData()
        fun showLoading()
        fun hideLoading()
        fun showEmptyData()
        fun setupAdapter()
        fun showTeamList(data: List<Team>)
        fun showLeagueList(data: Leagues)
        fun showFavoriteTeamList(data: List<Team>)
    }
    interface Presenter{
        fun onAttachedView(activity: TeamView.View)
        fun getAllLeague()
        fun getAllTeam(leagueName: String )
        fun getSearchedTeam(teamName: String )
        fun getFavoriteMatch()
    }
}