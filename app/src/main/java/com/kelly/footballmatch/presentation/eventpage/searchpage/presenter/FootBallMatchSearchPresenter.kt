package com.kelly.footballmatch.presentation.eventpage.searchpage.presenter

import android.content.Context
import com.kelly.footballmatch.external.util.CoroutineContextProvider
import com.kelly.footballmatch.presentation.eventpage.searchpage.contract.FootBallMatchSearchView
import com.kelly.footballmatch.presentation.eventpage.searchpage.usecase.searchmatchUseCase
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class FootBallMatchSearchPresenter( val context: Context, val mUseCase : searchmatchUseCase) : FootBallMatchSearchView.Presenter  {

    private var view: FootBallMatchSearchView.View? = null

    override fun onAttachedView(activity: FootBallMatchSearchView.View) {
        view = activity
        view?.initData()
    }

    override fun getFootBallMatchesSearch( eventName: String ) {
        view?.showLoading()

        async(context = CoroutineContextProvider().main) {
            var data = bg {
                mUseCase.getFootBallMatchesSearch(eventName)
            }.await()

            try {
                for (part in data.event!!) {
                    async(CoroutineContextProvider().main,CoroutineStart.LAZY) {
                        if(part.idHomeTeam != null ) {
                            var TeamHome = bg {
                                mUseCase.getTeamDetail(part.idHomeTeam!!)
                            }.await()

                            if (TeamHome.teams[0] != null) {
                                part.strHomeBadge = TeamHome.teams[0].strTeamBadge
                            }
                        }

                        if(part.idAwayTeam != null ) {
                            var TeamAway = bg {
                                mUseCase.getTeamDetail(part.idAwayTeam!!)
                            }.await()
                            if (TeamAway.teams[0] != null) {
                                part.strAwayBadge = TeamAway.teams[0].strTeamBadge
                            }
                        }
                    }.await()
                }

                view?.hideLoading()
                view?.showSearchedEvents(data.event!!)
            } catch (e: Exception) {
                view?.hideLoading()
                view?.showSearchedEvents(data.event!!)
            }

        }
    }
}