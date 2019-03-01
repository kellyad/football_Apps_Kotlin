package com.kelly.footballmatch.view.fragment

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.R
import com.kelly.footballmatch.list.team.players.PlayerAdapter
import com.kelly.footballmatch.list.team.players.PlayerThumbAdapter
import com.kelly.footballmatch.list.team.players.TeamPlayersPresenter
import com.kelly.footballmatch.list.team.players.TeamPlayersView
import com.kelly.footballmatch.list.team.players.detail.PlayerDetailActivity
import com.kelly.footballmatch.model.Player
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamPlayersFragment : Fragment(), AnkoComponent<Context>, TeamPlayersView {
    private var players             : MutableList<Player> = mutableListOf()

    private lateinit var adapter    : PlayerThumbAdapter
    private lateinit var listAdapter    : PlayerAdapter
    private lateinit var presenter      : TeamPlayersPresenter

    private lateinit var listPlayer     : RecyclerView
    private lateinit var progressBar    : ProgressBar
    private lateinit var swipeRefresh   : SwipeRefreshLayout

    private lateinit var teamTitleText  : String
    private lateinit var listImageView  : ImageView
    private lateinit var thumbImageView : ImageView

    companion object {

        fun newInstance(teamTitleText:String): TeamPlayersFragment {
            val fragment = TeamPlayersFragment()
            fragment.arguments = bundleOf("TeamTitle" to teamTitleText)

            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        teamTitleText = arguments?.get("TeamTitle") as String
        presenter = TeamPlayersPresenter(this, request, gson)
        presenter.getAllPlayer(teamTitleText)

        adapter = PlayerThumbAdapter(players) {
            ctx.startActivity<PlayerDetailActivity>("player" to it)
        }
        listAdapter = PlayerAdapter(players){
            ctx.startActivity<PlayerDetailActivity>("player" to it)
        }

            listPlayer.adapter = adapter
            listPlayer.layoutManager = android.support.v7.widget.GridLayoutManager(context, 2)


        swipeRefresh.onRefresh {
            presenter.getAllPlayer(teamTitleText)
        }

        listImageView.setOnClickListener{
                listPlayer.adapter = listAdapter
                listPlayer.layoutManager = android.support.v7.widget.LinearLayoutManager(context)
        }
        thumbImageView.setOnClickListener{
            listPlayer.adapter = adapter
            listPlayer.layoutManager = android.support.v7.widget.GridLayoutManager(context, 2)

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
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                {
                    orientation = LinearLayout.HORIZONTAL
                }
                listImageView = imageView {
                    setImageDrawable(ContextCompat.getDrawable(ctx,R.drawable.ic_view_list_black_24dp))
                    id = R.id.imageListView
                }.lparams {
                    leftMargin = dip(5)
                    width = dip(50)
                    height = dip(50)
                }

                textView {
                    text = "List View"
                }.lparams {
                    horizontalMargin = dip(15)
                    width = wrapContent
                    height = wrapContent


                }
                thumbImageView = imageView {
                    setImageDrawable(ContextCompat.getDrawable(ctx,R.drawable.ic_image_black_24dp))
                    id = R.id.imageThumbView
                }.lparams {
                    width = dip(50)
                    height = dip(50)
                }

                textView {
                    text = "Thumb View"
                }.lparams {
                    horizontalMargin = dip(15)
                    width = wrapContent
                    height = wrapContent


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

                    listPlayer = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        id = R.id.recyclerView
                    }

                    progressBar = progressBar {
                    }.lparams {
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
    override fun showTeamPlayer(data: List<Player>){
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
        listPlayer.scrollToPosition(0)

    }

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

}