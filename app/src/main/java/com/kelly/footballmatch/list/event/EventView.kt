package com.kelly.footballmatch.list.Event

import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.League

interface EventView {

    fun showLoading()
    fun hideLoading()
    fun showLastMatch(data: List<Event>)
    fun showNextMatch(data: List<Event>)
    fun showFavoriteMatch(data: List<Event>)
    fun showAllLeague(data : List<League>)


}

