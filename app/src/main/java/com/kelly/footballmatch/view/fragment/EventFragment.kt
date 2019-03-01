package com.kelly.footballmatch.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.R
import com.kelly.footballmatch.detail.DetailActivity
import com.kelly.footballmatch.list.Event.EventAdapter
import com.kelly.footballmatch.list.Event.EventPresenter
import com.kelly.footballmatch.list.Event.EventView
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.model.League
import com.kelly.footballmatch.model.repository.LocalRepositoryApi
import com.kelly.footballmatch.constant.Types
import com.google.gson.Gson

import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventFragment : Fragment(), AnkoComponent<Context>, EventView {

    private var events       : MutableList<Event> = mutableListOf()
    private var teamNameList : MutableList<String> = mutableListOf()

    private lateinit var presenter  : EventPresenter
    private lateinit var adapter    : EventAdapter

    private lateinit var spinnerLayout  : LinearLayout
    private lateinit var listEvent      : RecyclerView
    private lateinit var progressBar    : ProgressBar
    private lateinit var swipeRefresh   : SwipeRefreshLayout
    private lateinit var spinner        : Spinner

    private lateinit var league     : League
    private lateinit var Type       : Types
    private lateinit var leagueName   : String


    companion object {

        private const val TYPE_MATCHES = "TYPE_MATCHES"

        fun newInstance(fragmentType: Types): EventFragment {

            val fragment = EventFragment()
            fragment.arguments = bundleOf(TYPE_MATCHES to fragmentType)

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        val localRepo = LocalRepositoryApi(requireContext())
        val request = ApiRepository()
        val gson = Gson()

        Type = arguments?.get(TYPE_MATCHES) as Types

        presenter = EventPresenter(this, localRepo, request, gson)
        presenter.getAllLeague()


        adapter = EventAdapter(events){
            ctx.startActivity<DetailActivity>("event" to it)
        }

        listEvent.adapter = adapter

        with(listEvent) {
            adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        swipeRefresh.onRefresh {
            when (Type) {
                Types.LAST_MATCH -> presenter.getLastMatch(league.idLeague!!, league.strLeague!!)
                Types.UPCOMING_MATCH -> presenter.getNextMatch(league.idLeague!!, league.strLeague!!)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {

            lparams(width = matchParent, height = matchParent)

                orientation = LinearLayout.VERTICAL
                backgroundColor = R.color.material_blue_grey_800
                spinnerLayout = linearLayout {
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.LTGRAY

                spinner = spinner {
                    id = R.id.spinner
                    padding = dip(16)
                    minimumHeight = dip(80)
                }
            }

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
                        id = R.id.recyclerView
                    }

                    progressBar = progressBar {
                    }.lparams {
                        //centerHorizontally()
                        gravity = Gravity.CENTER
                    }
                }
            }

        }
    }

    override fun showLoading() {
        progressBar.visible()
        swipeRefresh.isRefreshing = true
        if (presenter.menu == 3) spinnerLayout.gone()
        else spinnerLayout.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        swipeRefresh.isRefreshing = false
    }

    override fun showLastMatch(data: List<Event>) {
        spinnerLayout.visible()
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showNextMatch(data: List<Event>) {
        spinnerLayout.visible()
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showFavoriteMatch(data: List<Event>) {
        spinnerLayout.gone()
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        listEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showAllLeague(data: List<League>) {

        for (leagueData in data){
            teamNameList.add(leagueData.strLeague!!)
        }

        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, teamNameList)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem as String

                for(leagueData in data){
                    if(leagueData.strLeague == leagueName){
                        league = leagueData
                    }
                }

                if( !league.equals(null) )
                {
                    when (Type) {
                        Types.LAST_MATCH -> presenter.getLastMatch(league.idLeague!! , league.strLeague!!)
                        Types.UPCOMING_MATCH -> presenter.getNextMatch(league.idLeague!!, league.strLeague!!)
                        Types.FAVORITES -> presenter.getFavoriteMatch()
                    }
                }
            }
        }
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }

}
