package com.dicoding.footballclub.model.repository

import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.db.FavoriteMatch

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
