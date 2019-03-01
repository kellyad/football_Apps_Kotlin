package com.kelly.footballmatch

import junit.framework.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito

class ApiRepositoryTest {

    @Test
    fun DoRequest() {
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
    @Test
    fun testNext15MatchesByLeagueId() {
        var LEAGUE_ID = "42454"

        val expected = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$LEAGUE_ID"
        val actual = TheSportsDBApi.getUpcomingMatch(LEAGUE_ID)
        assertEquals(expected, actual)
    }

}