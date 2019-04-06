package com.kelly.footballmatch.presentation.teampage.detailpage.module

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.presentation.teampage.detailpage.view.TeamDetailActivity
import com.kelly.footballmatch.presentation.teampage.homepage.contract.TeamView
import com.kelly.footballmatch.presentation.teampage.homepage.view.FootBallTeamFragment
import dagger.Module
import dagger.Provides


@Module
class teamdetailModule {
    @Provides
    @ActivityScope
    internal fun provideActvity(teamDetailActivity: TeamDetailActivity): TeamDetailActivity {
        return teamDetailActivity
    }
}