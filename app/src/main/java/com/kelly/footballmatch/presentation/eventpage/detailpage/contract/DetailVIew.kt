package com.kelly.footballmatch.presentation.eventpage.detailpage.contract

import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.teams.Team

interface DetailView {

    interface View{
        fun initData()
        fun showDataTeam(event: Event?)
        fun goToDetailActivity(team: Team)
        fun setFavoriteState(favList: List<Event>)
    }

    interface Presenter{
        fun deleteMatchFromDB(id:String?)
        fun checkMatchFromDB(id:String?)
        fun insertMatchFromDB(data : Event)
        fun calculateWinningResult( intHomeScore: String?, intAwayScore: String? ) : String
    }
}

