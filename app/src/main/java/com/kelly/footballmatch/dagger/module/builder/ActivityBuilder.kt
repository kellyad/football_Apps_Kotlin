package com.kelly.footballmatch.dagger.module.builder

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.presentation.eventpage.detailpage.module.detailpageModule
import com.kelly.footballmatch.presentation.eventpage.detailpage.view.DetailActivity
import com.kelly.footballmatch.presentation.eventpage.homepage.module.homePageModule
import com.kelly.footballmatch.presentation.eventpage.homepage.view.EventFragment
import com.kelly.footballmatch.presentation.eventpage.searchpage.module.searchmatchModule
import com.kelly.footballmatch.presentation.eventpage.searchpage.view.FootBallMatchSearchActivity
import com.kelly.footballmatch.presentation.playerpage.homepage.module.playerModule
import com.kelly.footballmatch.presentation.playerpage.homepage.view.TeamPlayersFragment
import com.kelly.footballmatch.presentation.teampage.detailpage.module.teamdetailModule
import com.kelly.footballmatch.presentation.teampage.detailpage.view.TeamDetailActivity
import com.kelly.footballmatch.presentation.teampage.homepage.module.hometeamModule
import com.kelly.footballmatch.presentation.teampage.homepage.view.FootBallTeamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [(homePageModule::class)])
    internal abstract fun bindHomePageActivity(): EventFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [(detailpageModule::class)])
    internal abstract fun bindDetailPageActivity(): DetailActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(searchmatchModule::class)])
    internal abstract fun bindSearchPageActivity(): FootBallMatchSearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(playerModule::class)])
    internal abstract fun bindPlayerPageActivity(): TeamPlayersFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [(hometeamModule::class)])
    internal abstract fun bindTeamPageActivity(): FootBallTeamFragment


    @ActivityScope
    @ContributesAndroidInjector(modules = [(teamdetailModule::class)])
    internal abstract fun bindTeamDetailPageActivity(): TeamDetailActivity

}