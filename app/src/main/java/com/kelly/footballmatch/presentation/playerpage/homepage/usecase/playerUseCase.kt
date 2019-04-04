package com.kelly.footballmatch.presentation.playerpage.homepage.usecase

import com.kelly.footballmatch.data.responses.players.TeamPlayers

interface playerUseCase {
    fun getAllPlayers(teamName: String): TeamPlayers
}