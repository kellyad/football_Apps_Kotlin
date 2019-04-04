package com.kelly.footballmatch.presentation.eventpage.searchpage.module

import com.kelly.footballmatch.presentation.eventpage.searchpage.presenter.FootBallMatchSearchPresenter
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchInteractor
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchUseCase
import org.koin.dsl.module.module

var searchmatchModule = module {

    factory <searchmatchUseCase> { searchmatchInteractor() }
    factory { FootBallMatchSearchPresenter(get(), get()) }
}