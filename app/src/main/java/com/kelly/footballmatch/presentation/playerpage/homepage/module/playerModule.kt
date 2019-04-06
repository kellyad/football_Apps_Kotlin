package com.kelly.footballmatch.presentation.playerpage.homepage.module

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.presentation.eventpage.searchpage.contract.FootBallMatchSearchView
import com.kelly.footballmatch.presentation.eventpage.searchpage.presenter.FootBallMatchSearchPresenter
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchInteractor
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchUseCase
import com.kelly.footballmatch.presentation.eventpage.searchpage.view.FootBallMatchSearchActivity
import com.kelly.footballmatch.presentation.playerpage.homepage.contract.TeamPlayersView
import com.kelly.footballmatch.presentation.playerpage.homepage.presenter.TeamPlayersPresenter
import com.kelly.footballmatch.presentation.playerpage.homepage.usecase.playerInteractor
import com.kelly.footballmatch.presentation.playerpage.homepage.usecase.playerUseCase
import com.kelly.footballmatch.presentation.playerpage.homepage.view.TeamPlayersFragment
import dagger.Module
import dagger.Provides
import org.koin.dsl.module.module

//var playerModule = module {
//
//    factory <playerUseCase> { playerInteractor() }
//    factory { TeamPlayersPresenter(get(), get()) }
//}

@Module
class playerModule {
    @Provides
    @ActivityScope
    internal fun provideActvity(homeplayerActivity: TeamPlayersFragment): TeamPlayersView.View {
        return homeplayerActivity
    }

//    @Provides
//    @ActivityScope
//    internal fun provideRouter(screenRouter: ScreenRouter, homepageActivity: HomepageActivity) =
//            HomepageRouter(screenRouter, homepageActivity)


//    @Provides
//    @ActivityScope
//    internal fun provideRepository(
//            mService: SportDbServices,
//            mScheduler: AppSchedulerProvider
//    ): SportDbRepository = SportDbDataStore(mService, mScheduler)

    @Provides
    @ActivityScope
    internal fun provideUsecase(): playerUseCase = playerInteractor()

    @Provides
    @ActivityScope
    internal fun providePresenter(
            view: TeamPlayersView.View,
            mUseCase: playerUseCase
    ) = TeamPlayersPresenter(view, mUseCase)
}