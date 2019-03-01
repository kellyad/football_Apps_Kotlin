package com.kelly.footballmatch.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.*
import com.kelly.footballmatch.Adapter.EventPagerAdapter

import com.kelly.footballmatch.R
import com.kelly.footballmatch.detail.DetailActivity
import com.kelly.footballmatch.list.Event.EventAdapter
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.search.match.FootBallMatchSearchActivity
import com.kelly.footballmatch.constant.Types

import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class FootBallMatchFragment : Fragment(), AnkoComponent<Context> {
    private var events   : MutableList<Event> = mutableListOf()

    private lateinit var adapter        : EventAdapter
    private lateinit var myTabLayout    : TabLayout
    private lateinit var myViewPager    : ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = EventAdapter(events){
            ctx.startActivity<DetailActivity>("event" to it)
        }


        with(activity as AppCompatActivity) {

            (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
            myViewPager.adapter = EventPagerAdapter(supportFragmentManager, arrayListOf(Types.UPCOMING_MATCH, Types.LAST_MATCH))
            myTabLayout.setupWithViewPager(myViewPager)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return createView(AnkoContext.create(ctx))
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.mn_search -> {
                context?.startActivity<FootBallMatchSearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {

            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL

            coordinatorLayout {
                lparams(matchParent, matchParent)

                appBarLayout {
                    lparams(matchParent, wrapContent)
                    myTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                        id = R.id.teamdetailtablayout
                        lparams(matchParent, wrapContent)
                        {
                            setSelectedTabIndicatorColor(resources.getColor(R.color.white))
                            tabMode = TabLayout.MODE_FIXED
                            gravity = Gravity.FILL

                        }
                    }
                }

                myViewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)

            }

        }
    }
}