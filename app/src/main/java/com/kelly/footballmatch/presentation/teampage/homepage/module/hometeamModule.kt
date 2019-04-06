package com.kelly.footballmatch.presentation.teampage.homepage.module

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.presentation.teampage.homepage.contract.TeamView
import com.kelly.footballmatch.presentation.teampage.homepage.presenter.TeamPresenter
import com.kelly.footballmatch.presentation.teampage.homepage.usecase.hometeamInteractor
import com.kelly.footballmatch.presentation.teampage.homepage.usecase.hometeamUseCase
import com.kelly.footballmatch.presentation.teampage.homepage.view.FootBallTeamFragment
import dagger.Module
import dagger.Provides
import org.koin.dsl.module.module

//var hometeamModule = module {
//
//    factory <hometeamUseCase> { hometeamInteractor() }
//    factory { TeamPresenter(get(), get()) }
//}


@Module
class hometeamModule {
    @Provides
    @ActivityScope
    internal fun provideActvity(hometeamActivity: FootBallTeamFragment): TeamView.View {
        return hometeamActivity
    }

//    @Provides
//    @ActivityScope
//    internal fun provideRouter(screenRouter: ScreenRouter, homepageActivity: HomepageActivity) =
//            HomepageRouter(screenRouter, homepageActivity)


//    @Provides
//    @ActivityScope
//    internal fun provideRepository(
//            mService: SportDbServices,a
//            mScheduler: AppSchedulerProvider
//    ): SportDbRepository = SportDbDataStore(mService, mScheduler)

    @Provides
    @ActivityScope
    internal fun provideUsecase(): hometeamUseCase = hometeamInteractor()

    @Provides
    @ActivityScope
    internal fun providePresenter(
            view: TeamView.View,
            mUseCase: hometeamUseCase,
            dbHelper: MyDatabaseOpenHelper
    ) = TeamPresenter(view, mUseCase, dbHelper)
}