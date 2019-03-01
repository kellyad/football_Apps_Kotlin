package com.dicoding.footballclub.list.Event

import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.League

interface EventView {

    fun showLoading()
    fun hideLoading()
    fun showLastMatch(data: List<Event>)
    fun showNextMatch(data: List<Event>)
    fun showFavoriteMatch(data: List<Event>)
    fun showAllLeague(data : List<League>)


}

