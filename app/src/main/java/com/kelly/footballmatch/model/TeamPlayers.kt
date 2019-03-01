package com.kelly.footballmatch.model

import com.google.gson.annotations.SerializedName

data class TeamPlayers(
        @SerializedName("player") val player: MutableList<Player>)