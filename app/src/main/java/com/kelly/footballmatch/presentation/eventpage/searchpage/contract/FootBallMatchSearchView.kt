package com.kelly.footballmatch.presentation.eventpage.searchpage.contract

import com.kelly.footballmatch.data.responses.events.Event

interface FootBallMatchSearchView {
    interface View {
        fun initData()
        fun showLoading()
        fun hideLoading()
        fun showEmptyData()
        fun setupAdapter()
        fun showSearchedEvents(data: List<Event>)
    }

    interface Presenter{
        fun onAttachedView(activity: FootBallMatchSearchView.View)
        fun getFootBallMatchesSearch( eventName: String )
    }
}