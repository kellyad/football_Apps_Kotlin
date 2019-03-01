package com.dicoding.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(

        @SerializedName("id") var id: Long?,
        @SerializedName("idLeague") var idLeague: String?,
        @SerializedName("idSoccerXML") var idSoccerXML: String?,
        @SerializedName("idTeam") var idTeam: String?,
        @SerializedName("intFormedYear") var intFormedYear: String?,
        @SerializedName("intLoved") var intLoved: String?,
        @SerializedName("intStadiumCapacity") var intStadiumCapacity: String?,
        @SerializedName("strAlternate") var strAlternate: String?,
        @SerializedName("strCountry") var strCountry: String?,
        @SerializedName("strDescriptionEN") var strDescriptionEN: String?,
        @SerializedName("strLeague") var strLeague: String?,
        @SerializedName("strStadium") var strStadium: String?,
        @SerializedName("strTeam") var strTeam: String?,
        @SerializedName("strTeamBadge") var strTeamBadge: String?,
        @SerializedName("strTeamBanner") var strTeamBanner: String?,
        @SerializedName("strTeamFanart1") var strTeamFanart1: String?
): Parcelable{
    companion object {
        const val TABLE_TEAM_FAVORITES = "TABLE_TEAM_FAVORITES"
        const val ID = "ID"
        const val IDLEAGUE = "IDLEAGUE"
        const val IDSOCCERXML = "IDSCOREXML"
        const val IDTEAM = "IDTEAM"
        const val INTFORMEDYEAR = "INTFORMEDYEAR"
        const val INTLOVED = "INTLOVED"
        const val INTSTADIUMCAPACITY = "INTSTADIUMCAPACITY"
        const val STRALTERNATE = "STRALTERNATE"
        const val STRCOUNTRY = "STRCOUNTRY"
        const val STRDESCRIPTIONEN = "STRDESCRIPTIONEN"
        const val STRLEAGUE = "STRLEAGUE"
        const val STRSTADIUM = "STRSTADIUM"
        const val STRTEAM = "STRTEAM"
        const val STRTEAMBADGE = "STRTEAMBADGE"
        const val STRTEAMBANNER = "STRTEAMBANNER"
        const val STRTEAMFANART1 = "STRTEAMFANART1"
    }
}
