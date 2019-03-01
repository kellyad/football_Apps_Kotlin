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
import android.widget.LinearLayout
import com.kelly.footballmatch.Adapter.FavoritePagerAdapter
import com.kelly.footballmatch.R
import com.kelly.footballmatch.detail.DetailActivity
import com.kelly.footballmatch.list.Event.EventAdapter
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.constant.Types
import kotlinx.android.synthetic.main.abc_dialog_title_material.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager

class FavoriteFragment: Fragment(), AnkoComponent<Context> {
    private var events      : MutableList<Event> = mutableListOf()

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
            myViewPager.adapter = FavoritePagerAdapter(supportFragmentManager, arrayListOf(Types.FAVORITES, Types.FAVORITES))
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