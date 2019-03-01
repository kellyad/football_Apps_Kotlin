package com.kelly.footballmatch.list

import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.Team

interface DetailView {

    interface View{

        fun showDataTeam(event: Event?)
        fun goToDetailActivity(team:Team)
        fun setFavoriteState(favList: List<Event>)
    }

    interface Presenter{
        fun deleteMatch(id:String?)
        fun checkMatch(id:String?)
        fun insertMatch(data : Event)
    }
}

