package com.dicoding.footballclub.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.LinearLayout
import com.dicoding.footballclub.Adapter.ViewPagerAdapter
import com.dicoding.footballclub.R
import com.dicoding.footballclub.detail.DetailActivity
import com.dicoding.footballclub.list.Event.EventAdapter
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.search.match.FootBallMatchSearchActivity
import com.dicoding.footballclub.util.Types
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class FavoriteFragment: Fragment(), AnkoComponent<Context> {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var myTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = EventAdapter(events){
            ctx.startActivity<DetailActivity>("event" to it)
        }


        with(activity as AppCompatActivity) {
            myViewPager.adapter = ViewPagerAdapter(supportFragmentManager,
                    mapOf(
                            getString(R.string.matches).capitalize() to EventFragment.newInstance(Types.FAVORITES),
                            getString(R.string.teams).capitalize() to FootBallTeamFragment.newInstance(Types.FAVORITES)
                    )
            )
            myTabLayout.setupWithViewPager(myViewPager)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
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
                            tabGravity = Gravity.BOTTOM
                            tabMode = TabLayout.MODE_FIXED
                            gravity = Gravity.BOTTOM

                        }
                    }
                }
                myViewPager = viewPager {
                    id = R.id.viewpager
                }.lparams(matchParent, matchParent)
                (myViewPager?.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()

            }

        }
    }
}