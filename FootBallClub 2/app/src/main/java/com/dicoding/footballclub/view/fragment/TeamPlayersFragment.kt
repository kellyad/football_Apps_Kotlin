package com.dicoding.footballclub.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.list.Event.EventPresenter
import com.dicoding.footballclub.list.team.TeamAdapter
import com.dicoding.footballclub.list.team.TeamPresenter
import com.dicoding.footballclub.list.team.detail.TeamDetailActivity
import com.dicoding.footballclub.list.team.players.PlayerAdapter
import com.dicoding.footballclub.list.team.players.TeamPlayersPresenter
import com.dicoding.footballclub.list.team.players.TeamPlayersView
import com.dicoding.footballclub.list.team.players.detail.PlayerDetailActivity
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.League
import com.dicoding.footballclub.model.Player
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamPlayersFragment : Fragment(), AnkoComponent<Context>, TeamPlayersView {
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var adapter: PlayerAdapter

    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamTitleText: String
    private lateinit var presenter : TeamPlayersPresenter

    companion object {

        fun newInstance(teamTitleText:String): TeamPlayersFragment {
            val fragment = TeamPlayersFragment()
            fragment.arguments = bundleOf("TeamTitle" to teamTitleText)

            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val localRepo = LocalRepositoryImpl(requireContext()!!)

        val request = ApiRepository()
        val gson = Gson()
        teamTitleText = arguments?.get("TeamTitle") as String
        presenter = TeamPlayersPresenter(this, request, gson)
        presenter.getAllPlayer(teamTitleText)

        adapter = PlayerAdapter(players) {
            ctx.startActivity<PlayerDetailActivity>("player" to it)
        }

        listPlayer.adapter = adapter

        with(listPlayer) {
            adapter = adapter
            layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        //super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                id = R.id.swipeRefresh
                frameLayout {
                    lparams(width = matchParent, height = matchParent)

                    listPlayer = recyclerView {
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

    }

    override fun hideLoading() {
        progressBar.invisible()
        swipeRefresh.isRefreshing = false
    }

    override fun showEmptyData(){

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

    override fun showTeamPlayer(data: List<Player>){
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
        listPlayer.scrollToPosition(0)

    }
}