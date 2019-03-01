package com.dicoding.footballclub

import java.net.URLEncoder

object TheSportsDBApi {
    fun getTeams(league: String?) : String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}" + "/search_all_teams.php?l=" +
                URLEncoder.encode(league, "UTF-8")
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/lookupteam.php?id=" + teamId
    }
    fun getAllTeam(leagueName: String): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/search_all_teams.php?l=" + leagueName
    }

    fun getSearchedTeam(teamName: String): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/searchteams.php?t=" + teamName
    }

    fun getAllPlayer(teamName: String): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/searchplayers.php?t=" + teamName
    }

    fun getLastMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/eventspastleague.php?id=" + leagueId
    }

    fun getUpcomingMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/eventsnextleague.php?id=" + leagueId
    }

    fun getEventById(eventId: String?): String{
        return BuildConfig.BASE_URL +
        "api/v1/json/${BuildConfig.API_KEY}/lookupevent.php?id=" + eventId
    }

    fun getAllLeague() : String{
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}/all_leagues.php"
    }
    fun getFootBallMatchesSearch(eventName: String): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.API_KEY}" + "/searchevents.php?e=" + eventName
    }

}