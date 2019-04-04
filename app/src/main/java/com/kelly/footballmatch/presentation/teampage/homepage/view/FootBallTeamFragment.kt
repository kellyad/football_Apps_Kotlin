package com.kelly.footballmatch.presentation.teampage.homepage.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*

import com.kelly.footballmatch.R
import com.kelly.footballmatch.presentation.teampage.detailpage.view.TeamDetailActivity
import com.kelly.footballmatch.data.responses.leagues.League
import com.kelly.footballmatch.data.responses.leagues.Leagues
import com.kelly.footballmatch.data.responses.teams.Team
import com.kelly.footballmatch.external.constant.Types
import com.kelly.footballmatch.external.adapter.TeamAdapter
import com.kelly.footballmatch.presentation.teampage.homepage.contract.TeamView
import com.kelly.footballmatch.presentation.teampage.homepage.presenter.TeamPresenter

import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.koin.android.ext.android.inject

class FootBallTeamFragment : Fragment(), AnkoComponent<Context>, TeamView.View {
    private var teams           : MutableList<Team> = mutableListOf()
    private var teamNameList    : MutableList<String> = mutableListOf()

    val presenter  : TeamPresenter by inject ()
    private lateinit var adapter    : TeamAdapter

    private lateinit var spinnerLayout  : LinearLayout
    private lateinit var listTeam       : RecyclerView
    private lateinit var progressBar    : ProgressBar
    private lateinit var swipeRefresh   : SwipeRefreshLayout
    private lateinit var spinner        : Spinner

    private lateinit var league         : League
    private lateinit var type           : Types
    private lateinit var teamName       : String

    companion object {
        private const val TYPE_TEAMS = "TYPE_TEAMS"

        fun newInstance(fragmentType: Types): FootBallTeamFragment {
            val fragment = FootBallTeamFragment()
            fragment.arguments = bundleOf(TYPE_TEAMS to fragmentType)

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onAttachedView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        when (type) {
            Types.LEAGUE -> {
                inflater?.inflate(R.menu.search_menu_view, menu)

                val searchMenu = menu?.findItem(R.id.mn_search_view)
                val searchView = searchMenu?.actionView as SearchView

                listenSearchView(searchView)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        //super.onCreate(savedInstanceState)

        linearLayout {

            lparams(width = matchParent, height = matchParent)
            backgroundColor = Color.LTGRAY
                orientation = LinearLayout.VERTICAL
                spinnerLayout = linearLayout {
                    orientation = LinearLayout.VERTICAL

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
                    listTeam = recyclerView {
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

    override fun initData() {
        type = arguments?.get(TYPE_TEAMS) as Types

        when (type) {
            Types.FAVORITES -> presenter.getFavoriteMatch()
            else -> presenter.getAllLeague()
        }
        setupAdapter()
    }

    override fun setupAdapter(){
        adapter = TeamAdapter(teams) {
            ctx.startActivity<TeamDetailActivity>("team" to it)
        }

        listTeam.adapter = adapter

        with(listTeam) {
            adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        swipeRefresh.onRefresh {
            when (type) {
                Types.FAVORITES -> presenter.getFavoriteMatch()
                else -> presenter.getAllTeam(league.idLeague!!)
            }
        }
    }

    private fun listenSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchedTeam(query.toString())

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().isEmpty()) {
                    spinnerLayout.visible()
                    presenter.getAllTeam(spinner.selectedItem.toString())
                }
                else spinnerLayout.gone()

                return true
            }
        })
    }

    override fun showLoading() {
        progressBar.visible()
        swipeRefresh.isRefreshing = true
        spinnerLayout.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        swipeRefresh.isRefreshing= false
    }

    override fun showEmptyData(){

    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        listTeam.scrollToPosition(0)
    }

    override fun showLeagueList(data: Leagues) {
        if (!data.equals(null)) {
            league = data.leagues[0]

            for (leagueData in data.leagues) {
                teamNameList.add(leagueData.strLeague!!)
            }
            spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, teamNameList)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    teamName = spinner.selectedItem as String
                    presenter.getAllTeam(teamName.toString())
                }
            }
        }
    }

    override fun showFavoriteTeamList(data: List<Team>) {
        spinnerLayout.gone()
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        listTeam.scrollToPosition(0)
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