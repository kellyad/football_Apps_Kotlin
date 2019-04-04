package com.kelly.footballmatch.presentation.eventpage.searchpage.usecase

import com.kelly.footballmatch.data.responses.matches.FootballMatchSearch
import com.kelly.footballmatch.data.responses.teams.TeamResponse

interface searchmatchUseCase {
    fun getFootBallMatchesSearch(eventName : String):FootballMatchSearch
    fun getTeamDetail(teamId : String?) : TeamResponse
}