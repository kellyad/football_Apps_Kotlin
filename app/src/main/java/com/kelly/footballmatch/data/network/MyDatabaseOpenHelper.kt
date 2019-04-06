package com.kelly.footballmatch.data.network


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.teams.Team
import org.jetbrains.anko.db.*
import javax.inject.Inject

class MyDatabaseOpenHelper @Inject constructor(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Event.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(p0: SQLiteDatabase) {
        // Here you create tables
        p0.createTable(Event.TABLE_FAVORITES, true,
                Event.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Event.ID_EVENT to TEXT,
                Event.DATE to TEXT,
                Event.STR_EVENT to TEXT,
                Event.INT_ROUND to TEXT,
                Event.STR_TIME to TEXT,

                // home team
                Event.HOME_ID to TEXT,
                Event.HOME_TEAM to TEXT,
                Event.HOME_SCORE to TEXT,
                Event.HOME_FORMATION to TEXT,
                Event.HOME_GOAL_DETAILS to TEXT,
                Event.HOME_SHOTS to TEXT,
                Event.HOME_RED_CARDS to TEXT,
                Event.HOME_YELLOW_CARDS to TEXT,
                Event.HOME_LINEUP_GOALKEEPER to TEXT,
                Event.HOME_LINEUP_DEFENSE to TEXT,
                Event.HOME_LINEUP_MIDFIELD to TEXT,
                Event.HOME_LINEUP_FORWARD to TEXT,
                Event.HOME_LINEUP_SUBSTITUTES to TEXT,
                Event.HOME_BADGE to TEXT,

                // away team
                Event.AWAY_ID to TEXT,
                Event.AWAY_TEAM to TEXT,
                Event.AWAY_SCORE to TEXT,
                Event.AWAY_FORMATION to TEXT,
                Event.AWAY_GOAL_DETAILS to TEXT,
                Event.AWAY_SHOTS to TEXT,
                Event.AWAY_RED_CARDS to TEXT,
                Event.AWAY_YELLOW_CARDS to TEXT,
                Event.AWAY_LINEUP_GOALKEEPER to TEXT,
                Event.AWAY_LINEUP_DEFENSE to TEXT,
                Event.AWAY_LINEUP_MIDFIELD to TEXT,
                Event.AWAY_LINEUP_FORWARD to TEXT,
                Event.AWAY_LINEUP_SUBSTITUTES to TEXT,
                Event.AWAY_BADGE to TEXT)

    p0.createTable(Team.TABLE_TEAM_FAVORITES, true,
    Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Team.IDLEAGUE to TEXT,
            Team.IDSOCCERXML to TEXT,
            Team.IDTEAM to TEXT,
            Team.INTFORMEDYEAR to TEXT,
            Team.INTLOVED to TEXT,
            Team.INTSTADIUMCAPACITY to TEXT,
            Team.STRALTERNATE to TEXT,
            Team.STRCOUNTRY to TEXT,
            Team.STRDESCRIPTIONEN to TEXT,
            Team.STRLEAGUE to TEXT,
            Team.STRSTADIUM to TEXT,
            Team.STRTEAM to TEXT,
            Team.STRTEAMBADGE to TEXT,
            Team.STRTEAMJERSEY to TEXT,
            Team.STRTEAMBANNER to TEXT,
            Team.STRTEAMFANART1 to TEXT

            )
}

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(Event.TABLE_FAVORITES, true)
        p0.dropTable(Team.TABLE_TEAM_FAVORITES, true)
    }
}
// Access property for Context
//val Context.database: MyDatabaseOpenHelper
//    get() = MyDatabaseOpenHelper.getInstance(applicationContext)