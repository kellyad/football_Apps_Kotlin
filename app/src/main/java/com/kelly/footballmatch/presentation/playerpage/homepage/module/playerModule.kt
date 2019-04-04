package com.kelly.footballmatch.presentation.playerpage.homepage.module

import com.kelly.footballmatch.presentation.playerpage.homepage.presenter.TeamPlayersPresenter
import com.kelly.footballmatch.presentation.playerpage.homepage.usecase.playerInteractor
import com.kelly.footballmatch.presentation.playerpage.homepage.usecase.playerUseCase
import org.koin.dsl.module.module

var playerModule = module {

    factory <playerUseCase> { playerInteractor() }
    factory { TeamPlayersPresenter(get(), get()) }
}