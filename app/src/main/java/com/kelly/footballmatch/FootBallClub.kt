package com.kelly.footballmatch

import android.app.Application
import com.kelly.footballmatch.koin.modules.Appmodule
import com.kelly.footballmatch.presentation.eventpage.detailpage.module.detailpageModule
import com.kelly.footballmatch.presentation.eventpage.homepage.module.homePageModule
import com.kelly.footballmatch.presentation.eventpage.searchpage.module.searchmatchModule
import com.kelly.footballmatch.presentation.playerpage.homepage.module.playerModule
import com.kelly.footballmatch.presentation.teampage.homepage.module.hometeamModule
import org.koin.android.ext.android.startKoin

class FootBallClub: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(homePageModule, detailpageModule, searchmatchModule, hometeamModule, playerModule
        ))
    }
}