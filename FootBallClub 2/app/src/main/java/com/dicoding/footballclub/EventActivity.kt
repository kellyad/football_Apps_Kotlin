package com.dicoding.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.*
import com.dicoding.footballclub.detail.DetailActivity
import com.dicoding.footballclub.R.color.colorAccent
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.list.Event.EventPresenter
import com.dicoding.footballclub.list.Event.EventView
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.League
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventActivity : AppCompatActivity(), EventView {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter

    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localRepo = LocalRepositoryImpl(applicationContext!!)
        relativeLayout {
            lparams(width = matchParent, height = matchParent)


                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(colorAccent,
                                android.R.color.holo_green_light,
                                android.R.color.holo_orange_light,
                                android.R.color.holo_red_light)
                        id = R.id.swipeRefresh
                        frameLayout {
                            lparams(width = matchParent, height = matchParent)

                            listEvent = recyclerView {
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                                id = R.id.recycler
                            }

                            progressBar = progressBar {
                            }.lparams {
                                //centerHorizontally()
                                gravity= Gravity.CENTER
                            }
                        }
                    }

//                }
            relativeLayout {
                lparams(width = matchParent, height = wrapContent){
                    alignParentBottom()
                }

            bottomNavigationView {
                inflateMenu(R.menu.bottom_navigation)
                fitsSystemWindows = true
                backgroundResource = R.color.white
                itemBackgroundResource = R.color.white
                selectedItemId = R.id.last_match
                itemIconTintList = resources.getColorStateList(R.color.colorPrimary)
                itemTextColor = resources.getColorStateList(R.color.colorPrimary)
                //itemTextColor = resources.getColorStateList(R.drawable.bottom_navigation_selector, theme)

                lparams(width = matchParent, height = wrapContent)
                {
                    backgroundColor = R.color.white
                    backgroundColorResource = R.color.white

                }
            }.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.last_match -> {
                        presenter.getLastMatch(leagueId = "4328")
                    }
                    R.id.upcoming_match -> {
                        presenter.getNextMatch(leagueId = "4328")
                    }

                    R.id.favorites -> {
                        presenter.getFavoriteMatch()
                    }
                }

                true
            }


            }

        }
        adapter = EventAdapter(events){
            startActivity<DetailActivity>("event" to it)
        }
        listEvent.adapter = adapter


        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, localRepo, request, gson)
        presenter.getLastMatch(leagueId = "4328")

        swipeRefresh.onRefresh {
            presenter.getLastMatch(leagueId= "4328")
        }
    }

    override fun showAllLeague(data: List<League>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLastMatch(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showNextMatch(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showFavoriteMatch(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

}
