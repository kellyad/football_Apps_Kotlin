package com.dicoding.footballclub.list

import com.dicoding.footballclub.model.Team

interface MainView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}