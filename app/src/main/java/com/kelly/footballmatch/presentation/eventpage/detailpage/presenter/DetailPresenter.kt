package com.kelly.footballmatch.presentation.eventpage.detailpage.presenter

import android.content.Context
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.external.util.CoroutineContextProvider
import com.kelly.footballmatch.presentation.eventpage.detailpage.contract.DetailView
import com.kelly.footballmatch.presentation.eventpage.detailpage.usecase.detailpageUseCase
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import javax.inject.Inject

class DetailPresenter
@Inject constructor(val view : DetailView.View, val mUseCase : detailpageUseCase, val dbHelper: MyDatabaseOpenHelper)
    : DetailView.Presenter {
    //private var view: DetailView.View? = null
    private var localRepositoryImpl = LocalRepositoryApi(dbHelper)

    fun showDataTeam(event: Event?){
        view?.showDataTeam(event)
    }

//    fun onAttachedView(activity: DetailView.View) {
//        view = activity
//        view?.initData()
//    }

    fun goToDetailActivity(teamId: String?){
        async(context = CoroutineContextProvider().main) {
            val data = bg {
               mUseCase.getTeamDetail(teamId)
            }
            view?.goToDetailActivity(data.await().teams[0])
        }
    }

    override fun calculateWinningResult( intHomeScore: String?, intAwayScore: String? ) : String {
        var roundText = ""
        if( intHomeScore.equals(null) || intHomeScore.equals("") ||
                intAwayScore.equals(null) || intAwayScore.equals("") ){
            roundText = "On Going"
        }else{
            if( (intHomeScore!!.toInt()) > (intAwayScore!!.toInt())){
                roundText = "Home Team Wins"
            }
            else if((intHomeScore!!.toInt() )== (intAwayScore!!.toInt())){
                roundText = "This Match Draw"
            }else {
                roundText = "Away Team Wins"
            }
        }
        return roundText
    }

    override fun deleteMatchFromDB(id: String?) {
        localRepositoryImpl.deleteData(id)
    }

    override fun checkMatchFromDB(id: String?) {
        view?.setFavoriteState(localRepositoryImpl.checkFavorite(id))
    }

    override fun insertMatchFromDB(data: Event) {
        localRepositoryImpl.insertData(data)
    }
}