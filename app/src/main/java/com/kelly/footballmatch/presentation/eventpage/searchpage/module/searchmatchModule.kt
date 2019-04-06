package com.kelly.footballmatch.presentation.eventpage.searchpage.module

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.presentation.eventpage.searchpage.contract.FootBallMatchSearchView
import com.kelly.footballmatch.presentation.eventpage.searchpage.presenter.FootBallMatchSearchPresenter
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchInteractor
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchUseCase
import com.kelly.footballmatch.presentation.eventpage.searchpage.view.FootBallMatchSearchActivity
import dagger.Module
import dagger.Provides
import org.koin.dsl.module.module

//var searchmatchModule = module {
//
//    factory <searchmatchUseCase> { searchmatchInteractor() }
//    factory { FootBallMatchSearchPresenter(get(), get()) }
//}


@Module
class searchmatchModule {
    @Provides
    @ActivityScope
    internal fun provideActvity(footBallMatchSearchActivity: FootBallMatchSearchActivity): FootBallMatchSearchView.View {
        return footBallMatchSearchActivity
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
    internal fun provideUsecase(): searchmatchUseCase = searchmatchInteractor()

    @Provides
    @ActivityScope
    internal fun providePresenter(
            view: FootBallMatchSearchView.View,
            mUseCase: searchmatchUseCase
    ) = FootBallMatchSearchPresenter(view, mUseCase)
}