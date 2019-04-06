package com.kelly.footballmatch.presentation.playerpage.homepage.presenter

import android.content.Context
import com.kelly.footballmatch.presentation.playerpage.homepage.contract.TeamPlayersView
import com.kelly.footballmatch.presentation.playerpage.homepage.usecase.playerUseCase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class TeamPlayersPresenter @Inject constructor(var view: TeamPlayersView.View, val mUseCase: playerUseCase): TeamPlayersView.Presenter  {

//    private var view: TeamPlayersView.View? = null


    override fun onAttachedView(activity: TeamPlayersView.View) {
        view = activity
        view?.initData()
    }

    override fun getAllPlayer(teamName: String) {
        view?.showLoading()
        doAsync {
            val data = mUseCase.getAllPlayers(teamName)

            uiThread {
                view?.hideLoading()
                view?.showTeamPlayer(data.player)
            }
        }
    }
}