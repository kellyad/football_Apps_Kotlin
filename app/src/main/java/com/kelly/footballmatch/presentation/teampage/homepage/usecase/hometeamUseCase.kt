package com.kelly.footballmatch.presentation.teampage.homepage.usecase

import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.teams.TeamResponse

interface hometeamUseCase {
    fun getAllLeague(): Leagues
    fun getAllTeam(leagueName: String): TeamResponse
    fun getSearchedTeam(teamName: String): TeamResponse
}