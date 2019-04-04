package com.kelly.footballmatch.presentation.playerpage.homepage.usecase

import com.google.gson.Gson
import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.data.responses.players.TeamPlayers

class playerInteractor(): playerUseCase {
    override fun getAllPlayers(teamName: String): TeamPlayers {
        return Gson().fromJson(ApiRepository()
                .doRequest(TheSportsDBApi.getAllPlayer(teamName)),
                TeamPlayers::class.java
        )
    }
}