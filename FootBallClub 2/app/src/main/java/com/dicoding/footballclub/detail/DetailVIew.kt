package com.dicoding.footballclub.list

import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.Team

interface DetailView {

    interface View{
        fun showDataTeam(event: Event?)
        fun displayTeamBadgeHome(team: List<Team>)
        fun displayTeamBadgeAway(team: List<Team>)
        fun setFavoriteState(favList: List<Event>)
    }

    interface Presenter{
        fun deleteMatch(id:String?)
        fun checkMatch(id:String?)
        fun insertMatch(data : Event)
    }
}

