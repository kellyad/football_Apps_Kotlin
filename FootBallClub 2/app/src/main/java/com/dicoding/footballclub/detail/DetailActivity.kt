package com.dicoding.footballclub.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.DetailView
import com.dicoding.footballclub.model.Event
import kotlinx.android.synthetic.main.detail_activity.*
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity(), DetailView.View {
    private lateinit var presenter: DetailPresenter
    lateinit var event: Event
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var isEvent : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        var request = ApiRepository()
        val gson = Gson()
        val localRepo = LocalRepositoryImpl(applicationContext)
        isEvent = intent.hasExtra("event")
        presenter = DetailPresenter(this, request,localRepo, gson)

        if(isEvent) {
            event = intent.getParcelableExtra<Event>("event")
            presenter.showDataTeam(event)
            presenter.checkMatch(event.idEvent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite){
                    if(isEvent) {
                        presenter.insertMatch(event)
                    }

                    toast("Event added to favorite")
                    isFavorite = !isFavorite
                }else{
                    if(isEvent) {
                        presenter.deleteMatch(event.idEvent)
                    }
                    toast("Event removed favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showDataTeam(event: Event?) {
        if(event != null) {
            supportActionBar?.title = event.strEvent
            dateScheduleTv.text = event.dateEvent
            homeNameTv.text = event.strHomeTeam
            homeScoreTv.text = event.intHomeScore
            awayNameTv.text = event.strAwayTeam
            awayScoreTv.text = event.intAwayScore

            homeScorerTv.text = event.strHomeGoalDetails
            awayScorerTv.text = event.strAwayGoalDetails

            gkHomeTv.text = event.strHomeLineupGoalkeeper
            gkAwayTv.text = event.strAwayLineupGoalkeeper

            defHomeTv.text = event.strHomeLineupDefense
            defAwayTv.text = event.strAwayLineupDefense

            midHomeTv.text = event.strHomeLineupMidfield
            midAwayTv.text = event.strAwayLineupMidfield

            forHomeTv.text = event.strHomeLineupForward
            forAwayTv.text = event.strAwayLineupForward

            subHomeTv.text = event.strHomeLineupSubstitutes
            subAwayTv.text = event.strAwayLineupSubstitutes

            shHomeTv.text = event.intHomeShots
            shAwayTv.text = event.intAwayShots

            homeFormationTv.text = event.strHomeFormation
            awayFormationTv.text = event.strAwayFormation

            presenter.getTeamsBadgeAway(event.idAwayTeam)
            presenter.getTeamsBadgeHome(event.idHomeTeam)
        }

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav)
    }
    override fun displayTeamBadgeHome(team: List<Team>) {
        Picasso.get().load(team[0].strTeamBadge).into(homeImg)
    }

    override fun displayTeamBadgeAway(team: List<Team>) {
        Picasso.get().load(team[0].strTeamBadge).into(awayImg)
    }

    override fun setFavoriteState(favList: List<Event>) {
        if(!favList.isEmpty()) isFavorite = true
    }

}