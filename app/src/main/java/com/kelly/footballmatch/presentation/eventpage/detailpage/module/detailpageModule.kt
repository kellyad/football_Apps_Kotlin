package com.kelly.footballmatch.presentation.eventpage.detailpage.module


import com.kelly.footballmatch.dagger.scope.ActivityScope
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.presentation.eventpage.detailpage.contract.DetailView
import com.kelly.footballmatch.presentation.eventpage.detailpage.presenter.DetailPresenter
import com.kelly.footballmatch.presentation.eventpage.detailpage.usecase.detailpageInteractor
import com.kelly.footballmatch.presentation.eventpage.detailpage.usecase.detailpageUseCase
import com.kelly.footballmatch.presentation.eventpage.detailpage.view.DetailActivity
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageInteractor
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageUseCase
import dagger.Module
import dagger.Provides
import org.koin.dsl.module.module
//
//var detailpageModule = module {
//
//    factory <detailpageUseCase> { detailpageInteractor() }
//    factory { DetailPresenter(get(), get()) }
//}

@Module
class detailpageModule {

    @Provides
    @ActivityScope
    internal fun provideActvity(detailPageActivity: DetailActivity): DetailView.View {
        return detailPageActivity
    }


    @Provides
    @ActivityScope
    internal fun provideUsecase(): detailpageUseCase = detailpageInteractor()
    @Provides
    @ActivityScope
    internal fun providePresenter(view: DetailView.View, mUseCase: detailpageUseCase, dbHelper: MyDatabaseOpenHelper) = DetailPresenter(view, mUseCase, dbHelper )
}