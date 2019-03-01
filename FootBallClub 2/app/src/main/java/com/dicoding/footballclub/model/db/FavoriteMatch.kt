package com.dicoding.footballclub.model.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FavoriteMatch (
        val id: Long?,
        val idEvent: String,
        val idHomeTeam: String,
        val idAwayTeam: String,
        val idHomeTeamName: String,
        val idAwayTeamName: String,
        val scrHomeTeam: String?,
        val scrAwayTeam: String?,
        val dateSchedule: String


):Parcelable
{
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_ID: String = "HOME_TEAM"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val DATE_SCHEDULE: String = "DATE_SCHEDULE"
    }
}
