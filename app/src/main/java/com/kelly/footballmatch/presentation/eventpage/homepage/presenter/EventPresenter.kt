package com.kelly.footballmatch.presentation.eventpage.homepage.presenter

import android.content.Context
import android.util.Log
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.external.util.CoroutineContextProvider

import com.kelly.footballmatch.presentation.eventpage.homepage.contract.homepageContract
import com.kelly.footballmatch.presentation.eventpage.homepage.usecase.homepageUseCase
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import javax.inject.Inject

class EventPresenter
@Inject constructor( val view: homepageContract.EventView, val mUsecase : homepageUseCase, val dbHelper: MyDatabaseOpenHelper) : homepageContract.Presenter {

    private val contextMain: CoroutineContextProvider = CoroutineContextProvider()
    private val localRepositoryImpl = LocalRepositoryApi(dbHelper)

    lateinit var event  : Event

    var menu = 1

//    override fun onAttachedView(activity: homepageContract.EventView) {
//        view = activity
//        view?.initData()
//    }

    override fun getLastMatch(leagueId: String?,leagueName: String?) {
        menu = 1
        view?.showLoading()
        async(contextMain.main){

            var data = bg {
                mUsecase.getLastMatch(leagueId)
            }
            var Teamsdata = bg {
                mUsecase.getAllTeam(leagueName)
            }
            for (part in data.await().events!!){
                var TeamHome = Teamsdata.await().teams.find{
                    it.strTeam == part.strHomeTeam
                }
                var TeamAway = Teamsdata.await().teams.find{
                    it.strTeam == part.strAwayTeam
                }
                part.strHomeBadge = TeamHome!!.strTeamBadge
                part.strAwayBadge = TeamAway!!.strTeamBadge
            }
                view?.hideLoading()
                view?.showLastMatch(data.await().events!!)
            }
    }
    override fun getNextMatch(leagueId: String?,leagueName:String?) {

        menu = 2
        view?.showLoading()
        async(contextMain.main) {

            val data = bg {
                mUsecase.getUpcomingMatch(leagueId)
            }
            var Teamsdata = bg {
                mUsecase.getAllTeam(leagueName)
            }
            for (part in data.await().events!!){
                var TeamHome = Teamsdata.await().teams.find{
                    it.strTeam == part.strHomeTeam
                }
                var TeamAway = Teamsdata.await().teams.find{
                    it.strTeam == part.strAwayTeam
                }
                part.strHomeBadge = TeamHome!!.strTeamBadge
                part.strAwayBadge = TeamAway!!.strTeamBadge
            }

                view?.hideLoading()
                view?.showNextMatch(data.await().events!!)
        }
    }
    override fun getFavoriteMatch() {
        menu = 3
        view?.showLoading()
        val favList = localRepositoryImpl.getMatchFromDb()
        var eventList: MutableList<Event> = mutableListOf()
        eventList.addAll(favList)
        view?.hideLoading()
        view?.showFavoriteMatch(eventList)

    }
    override fun getAllLeague(){
        Log.d("cek","amasuk")
        view?.showLoading()
        async(contextMain.main) {
            val data = bg {
                mUsecase.getAllLeague()
            }

            view?.hideLoading()
            view?.showAllLeague(data.await().leagues)
        }
    }
}