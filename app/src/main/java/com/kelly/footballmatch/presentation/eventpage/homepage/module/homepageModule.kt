package com.kelly.footballmatch.presentation.eventpage.homepage.module

import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.data.network.repository.LocalRepository
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.presentation.eventpage.homepage.contract.homepageContract
import com.kelly.footballmatch.presentation.eventpage.homepage.presenter.EventPresenter
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageInteractor
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageUseCase
import com.kelly.footballmatch.presentation.eventpage.homepage.view.EventFragment
import dagger.Module
import dagger.Provides
import org.koin.dsl.module.module


//val homePageModule = module {
//    factory <homepageUseCase> { homepageInteractor() }
//    factory { EventPresenter(get(), get()) }
//}

@Module
class homePageModule {
    @Provides
    @ActivityScope
    internal fun provideActvity(homepageActivity: EventFragment): homepageContract.EventView {
        return homepageActivity
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
    internal fun provideUsecase(): homepageUseCase = homepageInteractor()

    @Provides
    @ActivityScope
    internal fun providePresenter(
            view: homepageContract.EventView,
            useCase: homepageUseCase, dbHelper: MyDatabaseOpenHelper
    ) = EventPresenter(view, useCase, dbHelper)
}