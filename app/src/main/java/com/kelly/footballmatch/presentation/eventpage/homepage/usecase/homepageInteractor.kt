package com.kelly.footballmatch.presentation.eventpage.homepage.usecase

import com.google.gson.Gson
import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.matches.FootballMatch
import com.kelly.footballmatch.data.responses.teams.TeamResponse
import javax.inject.Inject

class homepageInteractor @Inject constructor() : homepageUseCase {

    override fun getLastMatch(leagueId : String?) : FootballMatch{
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getLastMatch(leagueId)),
                FootballMatch::class.java
        )
    }
    override fun getAllTeam(leagueName : String?) : TeamResponse{
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getAllTeam(leagueName!!)),
                TeamResponse::class.java
        )
    }
    override fun getUpcomingMatch(leagueId : String?) : FootballMatch{
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getUpcomingMatch(leagueId)),
                FootballMatch::class.java
        )
    }
    override fun getAllLeague(): Leagues{
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getAllLeague()),
                Leagues::class.java
        )
    }
}