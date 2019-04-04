package com.kelly.footballmatch.list.event

import com.kelly.footballmatch.data.network.repository.ApiRepository
import com.kelly.footballmatch.data.network.service.TheSportsDBApi
import com.kelly.footballmatch.presentation.eventpage.homepage.presenter.EventPresenter
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.matches.FootballMatch
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import com.kelly.footballmatch.external.util.TestContextProvider
import com.google.gson.Gson
import com.kelly.footballmatch.presentation.eventpage.homepage.contract.homepageContract
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    @Mock private lateinit var view: homepageContract.EventView
    @Mock private lateinit var localRepositoryImpl: LocalRepositoryApi
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var gson: Gson

    lateinit var footballMatch : FootballMatch
    private lateinit var presenter: EventPresenter

    private var event : MutableList<Event> =  mutableListOf()

    var leagueId = "4238"
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, localRepositoryImpl, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatch() {
        footballMatch = FootballMatch(event)
        Mockito.`when`(
                gson.fromJson(apiRepository
                .doRequest(TheSportsDBApi.getLastMatch(leagueId)),
                FootballMatch::class.java)
        ).thenReturn(footballMatch)
        presenter.getLastMatch(leagueId)

        Mockito.verify(view).showLoading()

        Mockito.verify(view).showLastMatch(event)

        Mockito.verify(view).hideLoading()

    }

    @Test
    fun getNextMatch() {
        footballMatch = FootballMatch(event)
        Mockito.`when`(
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getUpcomingMatch(leagueId)),
                        FootballMatch::class.java)
        ).thenReturn(footballMatch)
        presenter.getNextMatch(leagueId)
            Mockito.verify(view).showLoading()

        Mockito.verify(view).showNextMatch(event)

        Mockito.verify(view).hideLoading()


    }

    @Test
    fun getFavoriteMatch() {
        footballMatch = FootballMatch(event)
        val favList = localRepositoryImpl.getMatchFromDb()
        var eventList: MutableList<Event> = mutableListOf()
        eventList.addAll(favList)
        presenter.getFavoriteMatch()
        Mockito.verify(view).showLoading()

        Mockito.verify(view).showFavoriteMatch(eventList)

        Mockito.verify(view).hideLoading()

    }

}