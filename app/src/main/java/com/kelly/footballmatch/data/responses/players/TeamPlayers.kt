package com.kelly.footballmatch.data.responses.players

import com.google.gson.annotations.SerializedName

data class TeamPlayers(
        @SerializedName("player") val player: MutableList<Player>)