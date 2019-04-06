package com.kelly.footballmatch.presentation.eventpage.homepage.contract

import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.leagues.League

class homepageContract {
    interface EventView{
        fun initData()
        fun showLoading()
        fun hideLoading()
        fun showLastMatch(data: List<Event>)
        fun showNextMatch(data: List<Event>)
        fun showFavoriteMatch(data: List<Event>)
        fun showAllLeague(data : List<League>)
    }
    interface Presenter {
//        fun onAttachedView(activity: EventView)
        fun getLastMatch(leagueId: String?, leagueName: String?)
        fun getNextMatch(leagueId: String?, leagueName: String?)
        fun getFavoriteMatch()
        fun getAllLeague()
    }
}