package com.kelly.footballmatch.presentation.teampage.homepage.module

import com.kelly.footballmatch.presentation.teampage.homepage.presenter.TeamPresenter
import com.kelly.footballmatch.presentation.teampage.homepage.usecase.hometeamInteractor
import com.kelly.footballmatch.presentation.teampage.homepage.usecase.hometeamUseCase
import org.koin.dsl.module.module

var hometeamModule = module {

    factory <hometeamUseCase> { hometeamInteractor() }
    factory { TeamPresenter(get(), get()) }
}