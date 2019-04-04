package com.kelly.footballmatch.data.network.service

import com.kelly.footballmatch.BuildConfig
import com.kelly.footballmatch.external.constant.RestConstant
import java.net.URLEncoder

object TheSportsDBApi {
    fun getTeams(league: String?) : String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getTeams +
                URLEncoder.encode(league, "UTF-8")
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getTeamDetail + teamId
    }

    fun getAllTeam(leagueName: String?): String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getAllTeam + leagueName
    }

    fun getSearchedTeam(teamName: String): String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getSearchedTeam + teamName
    }

    fun getAllPlayer(teamName: String): String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getAllPlayer + teamName
    }

    fun getLastMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getLastMatch + leagueId
    }

    fun getUpcomingMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getUpcomingMatch + leagueId
    }

    fun getEventById(eventId: String?): String{
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getEventById + eventId
    }

    fun getAllLeague() : String{
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getAllLeague
    }
    fun getFootBallMatchesSearch(eventName: String): String {
        return BuildConfig.BASE_URL + RestConstant.SPORTDB.getFootBallMatchesSearch + eventName
    }

}