package com.kelly.footballmatch.presentation.eventpage.detailpage.usecase

import com.google.gson.Gson
import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.data.responses.teams.TeamResponse
import javax.inject.Inject

class detailpageInteractor @Inject constructor() :detailpageUseCase {
    override fun getTeamDetail(teamId : String?) : TeamResponse{
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
        )
    }
}