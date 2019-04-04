package com.kelly.footballmatch.data.responses.matches

import com.google.gson.annotations.SerializedName
import com.kelly.footballmatch.data.responses.events.Event

data class FootballMatch (
        @SerializedName("events") var events: List<Event>?)
