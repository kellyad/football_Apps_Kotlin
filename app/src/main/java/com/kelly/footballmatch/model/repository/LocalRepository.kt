package com.kelly.footballmatch.model.repository

import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.Team

interface LocalRepository {

    fun getMatchFromDb() : List<Event>

    fun insertData(data: Event)

    fun deleteData(eventId: String?)

    fun checkFavorite(eventId: String?) : List<Event>

    fun getTeamFromDb() : List<Team>

    fun insertFavoriteTeamData(data: Team)

    fun deleteFavoriteTeamData(teamId: String?)

    fun checkFavoriteTeam(teamId: String?) : List<Team>
}
