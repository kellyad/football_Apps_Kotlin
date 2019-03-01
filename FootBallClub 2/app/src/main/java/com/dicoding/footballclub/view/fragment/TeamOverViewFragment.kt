package com.dicoding.footballclub.view.fragment

import android.content.Context
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
import com.dicoding.footballclub.detail.DetailActivity
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.list.Event.EventPresenter
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.model.League
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.util.Types
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.onRefresh

class TeamOverViewFragment: Fragment(), AnkoComponent<Context> {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter

    private lateinit var spinnerLayout: LinearLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private lateinit var league: League
    private lateinit var overViewString: String
    private lateinit var overViewText: TextView


    companion object {
        private const val TYPE_TEAMS = "TYPE_TEAMS"

        fun newInstance(overViewText:String): TeamOverViewFragment {
            val fragment = TeamOverViewFragment()
            fragment.arguments = bundleOf(TYPE_TEAMS to overViewText)

            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val localRepo = LocalRepositoryImpl(requireContext()!!)

        val request = ApiRepository()
        val gson = Gson()
        overViewString = arguments?.get(TYPE_TEAMS) as String

        overViewText.text = overViewString
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        //super.onCreate(savedInstanceState)
        nestedScrollView() {
            lparams(width = matchParent , height = matchParent)
            overViewText = textView {
                id = R.id.team_year
                setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Subhead)
            }.lparams {
                padding = dip(16)
                height = wrapContent
                width = matchParent

            }
        }
    }
}