package com.kelly.footballmatch.search.match

import com.kelly.footballmatch.model.Event

interface FootBallMatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showSearchedEvents(data: List<Event>)
}