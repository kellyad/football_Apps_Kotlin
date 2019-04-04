package com.kelly.footballmatch.data.network.repository

import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.teams.Team

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
