package com.kelly.footballmatch.presentation.eventpage.searchpage.view

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

import com.kelly.footballmatch.R
import com.kelly.footballmatch.presentation.eventpage.detailpage.view.DetailActivity
import com.kelly.footballmatch.external.adapter.EventAdapter
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.presentation.eventpage.homepage.presenter.EventPresenter

import com.kelly.footballmatch.presentation.eventpage.searchpage.contract.FootBallMatchSearchView
import com.kelly.footballmatch.presentation.eventpage.searchpage.presenter.FootBallMatchSearchPresenter
import dagger.android.AndroidInjection
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.koin.android.ext.android.inject
import javax.inject.Inject

class FootBallMatchSearchActivity: AppCompatActivity(), FootBallMatchSearchView.View {

    @Inject
    lateinit var presenter: FootBallMatchSearchPresenter

    private var events         : MutableList<Event> = mutableListOf()
    private lateinit var listAdapter    : EventAdapter

    private lateinit var listEvent      : RecyclerView
    private lateinit var progressBar    : ProgressBar

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu_view, menu)

        val searchMenu = menu?.findItem(R.id.mn_search_view)
        val searchView = searchMenu?.actionView as SearchView

        searchView.isIconified = false
        listenSearchView(searchView)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = R.color.material_blue_grey_800


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
//        presenter.onAttachedView(this)

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
    override fun initData(){
        AndroidInjection.inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        events = mutableListOf()
        presenter.getFootBallMatchesSearch("")
        setupAdapter()
    }

    override fun setupAdapter(){
        listAdapter = EventAdapter(events) {
            startActivity<DetailActivity>("event" to it)
        }

        with(listEvent) {
            adapter = listAdapter
            layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        }
    }

    override fun showLoading() {
            progressBar.visible()
            listEvent.invisible()
    }

    override fun hideLoading() {
            progressBar.invisible()
            listEvent.visible()
    }

    override fun showEmptyData() {
            progressBar.invisible()
            listEvent.invisible()
    }


    override fun showSearchedEvents(data: List<Event>){
        events.clear()
        events.addAll(data)

        listAdapter.notifyDataSetChanged()
        listEvent.scrollToPosition(0)

    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

}