package com.kelly.footballmatch.external.constant

import com.kelly.footballmatch.BuildConfig

object RestConstant {

    object SPORTDB {
        const val BASE_APIKEY = "api/v1/json/${BuildConfig.API_KEY}"
        const val getTeams = "$BASE_APIKEY/search_all_teams.php"
        const val getTeamDetail = "$BASE_APIKEY/lookupteam.php?id="
        const val getImageTeams = "$BASE_APIKEY/searchteams.php"
        const val getAllTeam = "$BASE_APIKEY/search_all_teams.php?l="
        const val getSearchedTeam = "$BASE_APIKEY/searchteams.php?t="
        const val getAllPlayer = "$BASE_APIKEY/searchplayers.php?t="
        const val getAllLeague = "$BASE_APIKEY/all_leagues.php"
        const val getAllLeagues = "$BASE_APIKEY/search_all_leagues.php?s=Soccer"
        const val getUpcomingMatch = "$BASE_APIKEY/eventsnextleague.php?id="
        const val getLastMatch = "$BASE_APIKEY/eventspastleague.php?id="
        const val getEventById = "$BASE_APIKEY/lookupevent.php?id="
        const val getFootBallMatchesSearch = "$BASE_APIKEY/searchevents.php?e="

    }
}