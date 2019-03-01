package com.dicoding.footballclub.model.repository

import android.content.Context
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete

class LocalRepositoryImpl(private val context: Context) : LocalRepository {

    override fun checkFavorite(eventId: String?): List<Event> {

        return context.database.use {
            val result = select(Event.TABLE_FAVORITES)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to eventId.toString())
            val favorite = result.parseList(classParser<Event>())
            favorite
        }
    }


    override fun deleteData(eventId: String?) {
        context.database.use{
            delete(Event.TABLE_FAVORITES, "(ID_EVENT = {id})",
                    "id" to eventId.toString())
        }
    }

    override fun insertData(data: Event) {
        context.database.use {
            insert(Event.TABLE_FAVORITES,
                    Event.ID_EVENT to data.idEvent,
                    Event.DATE to data.dateEvent,
                    Event.STR_EVENT to data.strEvent,

                    // home team
                    Event.HOME_ID to data.idHomeTeam,
                    Event.HOME_TEAM to data.strHomeTeam,
                    Event.HOME_SCORE to data.intHomeScore,
                    Event.HOME_FORMATION to data.strHomeFormation,
                    Event.HOME_GOAL_DETAILS to data.strHomeGoalDetails,
                    Event.HOME_SHOTS to data.intHomeShots,
                    Event.HOME_LINEUP_GOALKEEPER to data.strHomeLineupGoalkeeper,
                    Event.HOME_LINEUP_DEFENSE to data.strHomeLineupDefense,
                    Event.HOME_LINEUP_MIDFIELD to data.strHomeLineupMidfield,
                    Event.HOME_LINEUP_FORWARD to data.strHomeLineupForward,
                    Event.HOME_LINEUP_SUBSTITUTES to data.strHomeLineupSubstitutes,

                    // away team
                    Event.AWAY_ID to data.idAwayTeam,
                    Event.AWAY_TEAM to data.strAwayTeam,
                    Event.AWAY_SCORE to data.intAwayScore,
                    Event.AWAY_FORMATION to data.strAwayFormation,
                    Event.AWAY_GOAL_DETAILS to data.strAwayGoalDetails,
                    Event.AWAY_SHOTS to data.intAwayShots,
                    Event.AWAY_LINEUP_GOALKEEPER to data.strAwayLineupGoalkeeper,
                    Event.AWAY_LINEUP_DEFENSE to data.strAwayLineupDefense,
                    Event.AWAY_LINEUP_MIDFIELD to data.strAwayLineupMidfield,
                    Event.AWAY_LINEUP_FORWARD to data.strAwayLineupForward,
                    Event.AWAY_LINEUP_SUBSTITUTES to data.strAwayLineupSubstitutes)

        }
    }

    override fun getTeamFromDb(): MutableList<Team> {
         val favoriteTeamList :MutableList<Team> = mutableListOf()
        context.database.use {
            val resultTeam  = select(Team.TABLE_TEAM_FAVORITES)
            val favoriteTeam = resultTeam.parseList(classParser<Team>())
            favoriteTeamList.addAll(favoriteTeam)
        }
        return favoriteTeamList
    }

    override fun checkFavoriteTeam(teamId: String?): List<Team> {

        return context.database.use {
            val resultTeam = select(Team.TABLE_TEAM_FAVORITES)
                    .whereArgs("(IDTEAM = {id})",
                            "id" to teamId.toString())
            val favoriteTeam = resultTeam.parseList(classParser<Team>())
            favoriteTeam
        }
    }


    override fun deleteFavoriteTeamData(teamId: String?) {
        context.database.use{
            delete(Team.TABLE_TEAM_FAVORITES, "(IDTEAM = {id})",
                    "id" to teamId.toString())
        }
    }

    override fun insertFavoriteTeamData(data: Team) {
        context.database.use {
            insert(Team.TABLE_TEAM_FAVORITES,
            Team.IDTEAM to data.idTeam,
            Team.IDLEAGUE to data.idLeague,
            Team.STRTEAM to data.strTeam,
            Team.IDSOCCERXML to data.idSoccerXML,
            Team.INTFORMEDYEAR to data.intFormedYear,
            Team.INTLOVED to data.intLoved,
            Team.INTSTADIUMCAPACITY to data.intStadiumCapacity,
            Team.STRALTERNATE to data.strAlternate,
            Team.STRCOUNTRY to data.strCountry,
            Team.STRDESCRIPTIONEN to data.strDescriptionEN,
            Team.STRLEAGUE to data.strLeague,
            Team.STRSTADIUM to data.strStadium,
            Team.STRTEAMBADGE to data.strTeamBadge,
            Team.STRTEAMBANNER to data.strTeamBanner,
            Team.STRTEAMFANART1  to data.strTeamFanart1 )

        }
    }

    override fun getMatchFromDb(): MutableList<Event> {
        val favoriteList :MutableList<Event> = mutableListOf()
        context.database.use {
            val result = select(Event.TABLE_FAVORITES)
            val favorite = result.parseList(classParser<Event>())
            favoriteList.addAll(favorite)
        }
        return favoriteList
    }
}