package com.kelly.footballmatch.presentation.eventpage.detailpage.module


import com.kelly.footballmatch.presentation.eventpage.detailpage.presenter.DetailPresenter
import com.kelly.footballmatch.presentation.eventpage.detailpage.usecase.detailpageInteractor
import com.kelly.footballmatch.presentation.eventpage.detailpage.usecase.detailpageUseCase
import org.koin.dsl.module.module

var detailpageModule = module {

    factory <detailpageUseCase> { detailpageInteractor() }
    factory { DetailPresenter(get(), get()) }
}