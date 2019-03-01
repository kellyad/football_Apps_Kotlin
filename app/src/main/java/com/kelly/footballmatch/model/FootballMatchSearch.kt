package com.kelly.footballmatch.model

import com.google.gson.annotations.SerializedName

data class FootballMatchSearch (
        @SerializedName("event") var event: List<Event>?)
