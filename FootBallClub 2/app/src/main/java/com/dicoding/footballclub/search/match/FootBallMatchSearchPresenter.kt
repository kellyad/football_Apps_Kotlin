package com.dicoding.footballclub.search.match

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.model.FootballMatchSearch
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FootBallMatchSearchPresenter(private val view: FootBallMatchSearchView,
                                   private val apiRepository: ApiRepository,
                                   private val gson: Gson)  {
    fun getFootBallMatchesSearch(eventName: String = "") {
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getFootBallMatchesSearch(eventName)),
                    FootballMatchSearch::class.java
            )

            uiThread {
                view.hideLoading()

                try {
                    view.showSearchedEvents(data.event!!)
                } catch (e: Exception) {
                    view.showEmptyData()
                }
            }
        }
    }
}