package com.dicoding.footballclub.search.match

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.R
import com.dicoding.footballclub.detail.DetailActivity
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FootBallMatchSearchActivity: AppCompatActivity(), FootBallMatchSearchView  {


    private lateinit var events: MutableList<Event>
    private lateinit var listAdapter: EventAdapter

    private lateinit var presenter: FootBallMatchSearchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localRepo = LocalRepositoryImpl(applicationContext!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val request = ApiRepository()
        val gson = Gson()

        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
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
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
        presenter = FootBallMatchSearchPresenter(this, request, gson)

        events = mutableListOf()
        listAdapter = EventAdapter(events) {
            startActivity<DetailActivity>("event" to it)
        }

        with(listEvent) {
            adapter = listAdapter
            layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        }

        presenter.getFootBallMatchesSearch()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu_view, menu)

        val searchMenu = menu?.findItem(R.id.mn_search_view)
        val searchView = searchMenu?.actionView as SearchView

        searchView.isIconified = false

        listenSearchView(searchView)

        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
            progressBar.visible()
            listEvent.invisible()
//            tv_empty.invisible()
    }

    override fun hideLoading() {
            progressBar.invisible()
            listEvent.visible()
//            tv_empty.invisible()
    }

    override fun showEmptyData() {
            progressBar.invisible()
            listEvent.invisible()
    }
    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    private fun listenSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getFootBallMatchesSearch(query.toString())

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().isEmpty()) presenter.getFootBallMatchesSearch(query.toString())

                return true
            }
        })
    }
    override fun showSearchedEvents(data: List<Event>){
        events.clear()
        events.addAll(data)
        listAdapter.notifyDataSetChanged()
        listEvent.scrollToPosition(0)

    }
}