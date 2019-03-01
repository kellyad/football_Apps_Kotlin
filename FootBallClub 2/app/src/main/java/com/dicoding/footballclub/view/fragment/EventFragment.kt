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
import android.widget.*
import com.dicoding.footballclub.ApiRepository
import com.dicoding.footballclub.R
import com.dicoding.footballclub.detail.DetailActivity
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.list.Event.EventPresenter
import com.dicoding.footballclub.list.Event.EventView
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.League
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.Types
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventFragment : Fragment(), AnkoComponent<Context>, EventView {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter

    private lateinit var spinnerLayout: LinearLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private lateinit var league: League
    private lateinit var Type: Types


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

        val localRepo = LocalRepositoryImpl(requireContext()!!)

        val request = ApiRepository()
        val gson = Gson()
        Type = arguments?.get(TYPE_MATCHES) as Types

        presenter = EventPresenter(this, localRepo, request, gson)
        presenter.getAllLeague()
        // spinner.loadFirstText(context!!)

        events = mutableListOf()

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
                Types.UPCOMING_MATCH -> presenter.getLastMatch(league.idLeague!!)
                Types.LAST_MATCH -> presenter.getNextMatch(league.idLeague!!)
            }
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
    override fun showAllLeague(data: List<League>) {
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, data)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as League

                when (Type) {
                    Types.UPCOMING_MATCH -> presenter.getLastMatch(league.idLeague!!)
                    Types.LAST_MATCH -> presenter.getNextMatch(league.idLeague!!)
                    Types.FAVORITES -> presenter.getFavoriteMatch()
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