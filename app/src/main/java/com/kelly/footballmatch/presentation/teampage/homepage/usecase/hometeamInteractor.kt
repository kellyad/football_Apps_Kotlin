package com.kelly.footballmatch.presentation.teampage.homepage.usecase

import com.google.gson.Gson
import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.teams.TeamResponse

class hometeamInteractor () : hometeamUseCase {
    override fun getAllTeam(leagueName: String): TeamResponse {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getAllTeam(leagueName)),
                TeamResponse::class.java
        )
    }

    override fun getAllLeague(): Leagues {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getAllLeague()),
                Leagues::class.java
        )
    }

    override fun getSearchedTeam(teamName: String): TeamResponse {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getSearchedTeam(teamName)),
                TeamResponse::class.java
        )
    }
}