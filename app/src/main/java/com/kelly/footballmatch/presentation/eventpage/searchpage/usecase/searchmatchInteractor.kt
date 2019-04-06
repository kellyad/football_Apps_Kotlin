package com.kelly.footballmatch.presentation.eventpage.searchpage.usecase

import com.google.gson.Gson
import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.data.responses.matches.FootballMatchSearch
import com.kelly.footballmatch.data.responses.teams.TeamResponse
import javax.inject.Inject

class searchmatchInteractor @Inject constructor() : searchmatchUseCase  {
    override fun getFootBallMatchesSearch(eventName: String): FootballMatchSearch {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getFootBallMatchesSearch(eventName)),
                FootballMatchSearch::class.java
        )
    }

    override fun getTeamDetail(teamId : String?) : TeamResponse {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
        )
    }
}