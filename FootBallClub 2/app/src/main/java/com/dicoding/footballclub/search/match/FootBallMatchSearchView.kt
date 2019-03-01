package com.dicoding.footballclub.search.match

import com.dicoding.footballclub.model.Event

interface FootBallMatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showSearchedEvents(data: List<Event>)
}