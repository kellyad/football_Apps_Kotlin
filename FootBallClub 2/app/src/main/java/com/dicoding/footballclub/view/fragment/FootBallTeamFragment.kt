package com.dicoding.footballclub.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.team.TeamAdapter
import com.dicoding.footballclub.list.team.TeamPresenter
import com.dicoding.footballclub.list.team.TeamView
import com.dicoding.footballclub.list.team.detail.TeamDetailActivity
import com.dicoding.footballclub.model.League
import com.dicoding.footballclub.model.Leagues
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.Types
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FootBallTeamFragment : Fragment(), AnkoComponent<Context>, TeamView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter

    private lateinit var spinnerLayout: LinearLayout
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private lateinit var league: League
    private lateinit var type : Types

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

        val localRepo = LocalRepositoryImpl(requireContext()!!)

        val request = ApiRepository()
        val gson = Gson()

        type = arguments?.get(TYPE_TEAMS) as Types
        presenter = TeamPresenter(this, localRepo,request, gson)
        when (type) {
            Types.FAVORITES -> presenter.getFavoriteMatch()
            else -> presenter.getAllLeague()
        }



        teams = mutableListOf()

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

            orientation = LinearLayout.VERTICAL

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
                } else spinnerLayout.gone()

                return true
            }
        })
    }

    override fun showLoading() {
        progressBar.visible()

        spinnerLayout.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
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
        spinner.adapter = ArrayAdapter(requireContext()!!, android.R.layout.simple_spinner_dropdown_item, data.leagues)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as League

                presenter.getAllTeam(league.strLeague.toString())
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