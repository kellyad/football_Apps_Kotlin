package com.kelly.footballmatch.detail

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.TheSportsDBApi
import com.kelly.footballmatch.list.DetailView
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.Team
import com.kelly.footballmatch.model.TeamResponse
import com.kelly.footballmatch.model.repository.LocalRepositoryApi
import com.kelly.footballmatch.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock private lateinit var view: DetailView.View
    @Mock private lateinit var localRepositoryImpl: LocalRepositoryApi
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var gson: Gson

    private lateinit var presenter: DetailPresenter

    private lateinit var event : Event
    private lateinit var teamResponse : TeamResponse

    private var teams : MutableList<Team> =  mutableListOf()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, localRepositoryImpl)
        event = Event(
                id = 1,
                idEvent= "576578",
                strEvent= "Huddersfield Town vs Fulham",
                strHomeTeam= "Huddersfield Town",
                strAwayTeam= "Fulham",
                intHomeScore= "1",
                intAwayScore= "0",
                intRound = "16",
                strAwayRedCards = "0",
                strAwayYellowCards = "0",
                strAwayBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png",
                strHomeBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png",
                strHomeRedCards = "0",
                strHomeYellowCards = "0",
                strTime = "00:00",
                strHomeGoalDetails= "29':Christopher Schindler;",
                strHomeLineupGoalkeeper= "Jonas Loessl; ",
                strHomeLineupDefense= "Erik Durm; Mathias Joergensen; Christopher Schindler; Chris Loewe; ",
                strHomeLineupMidfield= "Philip Billing; Jonathan Hogg; Isaac Mbenza; Alex Pritchard; Aaron Mooy; ",
                strHomeLineupForward= "Laurent Depoitre; ",
                strHomeLineupSubstitutes= "Ben Hamer; Tom Smith; Juninho Bacuna; Ramadan Sobhi; Rajiv van La Parra; Isaac Mbenza; Laurent Depoitre; ",
                strHomeFormation= "",
                strAwayGoalDetails= "",
                strAwayLineupGoalkeeper= "Sergio Rico; ",
                strAwayLineupDefense= "Timothy Fosu-Mensah; Denis Odoi; Tim Ream; Ryan Sessegnon; ",
                strAwayLineupMidfield= "Tom Cairney; Andre Anguissa; Jean Michael Seri; ",
                strAwayLineupForward= "Luciano Dario Vietto; Aleksandar Mitrovic; Andre Schuerrle; ",
                strAwayLineupSubstitutes= "Marcus Bettinelli; Calum Chambers; Tim Ream; Cyrus Christie; Alfie Mawson; Kevin McDonald; Stefan Johansen; ",
                strAwayFormation= "",
                intHomeShots= "2",
                intAwayShots= "1",
                dateEvent= "2018-11-05",
                idHomeTeam= "133932",
                idAwayTeam= "133600"
        )
    }

    @Test
    fun showDataTeam() {
        presenter.showDataTeam(event)
        Mockito.verify(view).showDataTeam(event)

    }


}