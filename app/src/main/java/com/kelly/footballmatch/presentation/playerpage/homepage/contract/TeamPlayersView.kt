package com.kelly.footballmatch.presentation.playerpage.homepage.contract

import com.kelly.footballmatch.data.responses.players.Player

interface TeamPlayersView {
    interface View {
        fun initData()
        fun showLoading()
        fun hideLoading()
        fun setupAdapter()
        fun showEmptyData()
        fun showTeamPlayer(data: List<Player>)
    }
    interface Presenter{
        fun onAttachedView(activity: View)
        fun getAllPlayer(teamName: String)
    }

}