package com.kelly.footballmatch.presentation.eventpage.homepage.usecase

import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.matches.FootballMatch
import com.kelly.footballmatch.data.responses.teams.TeamResponse

interface homepageUseCase {
    fun getLastMatch(leagueId : String?) : FootballMatch
    fun getAllTeam(leagueName : String?) : TeamResponse
    fun getUpcomingMatch(leagueId : String?) : FootballMatch
    fun getAllLeague(): Leagues


}