package com.dicoding.footballclub.detail

import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.TheSportsDBApi
import com.dicoding.footballclub.list.DetailView
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.TeamResponse
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock private lateinit var view: DetailView.View
    @Mock private lateinit var localRepositoryImpl: LocalRepositoryImpl
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var gson: Gson

    private lateinit var presenter: DetailPresenter

    private lateinit var event : Event
    private lateinit var teamResponse : TeamResponse

    private var teams : MutableList<Team> =  mutableListOf()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, localRepositoryImpl,gson, TestContextProvider())
        event = Event(
            id = 1,
            idEvent= "576578",
            strEvent= "Huddersfield Town vs Fulham",
            strHomeTeam= "Huddersfield Town",
            strAwayTeam= "Fulham",
            intHomeScore= "1",
            intAwayScore= "0",
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

    @Test
    fun getTeamsBadgeHome() {
        teamResponse = TeamResponse(teams)
    Mockito.`when`(
            gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getTeamDetail(event.idHomeTeam)),
                    TeamResponse::class.java)
    ).thenReturn(teamResponse)
    presenter.getTeamsBadgeAway(event.idHomeTeam)
    Mockito.verify(view).displayTeamBadgeAway(teamResponse.teams!!)
    }

    @Test
    fun getTeamsBadgeAway() {
        teamResponse = TeamResponse(teams)
        Mockito.`when`(
                gson.fromJson(apiRepository
                        .doRequest(TheSportsDBApi.getTeamDetail(event.idAwayTeam)),
                        TeamResponse::class.java)
        ).thenReturn(teamResponse)
        presenter.getTeamsBadgeAway(event.idAwayTeam)
        Mockito.verify(view).displayTeamBadgeAway(teamResponse.teams!!)
    }

}