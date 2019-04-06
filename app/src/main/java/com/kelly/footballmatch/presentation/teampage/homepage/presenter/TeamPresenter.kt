package com.kelly.footballmatch.presentation.teampage.homepage.presenter

import android.content.Context
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.data.responses.teams.Team
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.presentation.teampage.homepage.contract.TeamView
import com.kelly.footballmatch.presentation.teampage.homepage.usecase.hometeamUseCase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class TeamPresenter @Inject constructor(var view: TeamView.View, val mUseCase: hometeamUseCase, val dbHelper: MyDatabaseOpenHelper) : TeamView.Presenter {
//    private var view: TeamView.View? = null
    private var localRepositoryImpl = LocalRepositoryApi(dbHelper)


//    override fun onAttachedView(activity: TeamView.View) {
//        view = activity
//        view?.initData()
//    }

    override fun getAllLeague() {
        view?.showLoading()

        doAsync {
            val data = mUseCase.getAllLeague()

            uiThread {
                view?.hideLoading()
                view?.showLeagueList(data)
            }
        }
    }

    override fun getAllTeam(leagueName: String ) {
        view?.showLoading()

        doAsync {
            val data = mUseCase.getAllTeam(leagueName)
            uiThread {
                view?.hideLoading()

                try {
                    view?.showTeamList(data.teams)
                } catch (e: Exception) {
                    view?.showEmptyData()
                }
            }
        }
    }

    override fun getSearchedTeam(teamName: String ) {
        view?.showLoading()

        doAsync {
            val data = mUseCase.getSearchedTeam(teamName)
            uiThread {
                view?.hideLoading()

                try {
                    view?.showTeamList(data.teams)
                } catch (e: Exception) {
                    view?.showEmptyData()
                }
            }
        }
    }

    override fun getFavoriteMatch() {
        view?.showLoading()
        doAsync {
            val favList = localRepositoryImpl.getTeamFromDb()
            var teamList: MutableList<Team> = mutableListOf()
            teamList.addAll(favList)
            uiThread {
                view?.hideLoading()
                view?.showFavoriteTeamList(teamList)
            }
        }

    }

}