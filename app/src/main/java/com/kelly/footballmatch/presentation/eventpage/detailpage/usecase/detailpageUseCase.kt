package com.kelly.footballmatch.presentation.eventpage.detailpage.usecase

import com.kelly.footballmatch.data.responses.teams.TeamResponse


interface detailpageUseCase {
    fun getTeamDetail(teamId : String?) : TeamResponse

}