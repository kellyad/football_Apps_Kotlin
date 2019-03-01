package com.dicoding.footballclub.model

import com.google.gson.annotations.SerializedName

data class FootballMatch (
        @SerializedName("events") var events: List<Event>?)
