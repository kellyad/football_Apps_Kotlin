package com.kelly.footballmatch.presentation.eventpage.homepage.module

import com.kelly.footballmatch.data.network.repository.LocalRepository
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.presentation.eventpage.homepage.presenter.EventPresenter
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageInteractor
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageUseCase
import org.koin.dsl.module.module


val homePageModule = module {
    factory <homepageUseCase> { homepageInteractor() }
    factory { EventPresenter(get(), get()) }
}