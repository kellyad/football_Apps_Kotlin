package com.dicoding.footballclub.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.View
import android.widget.*
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.MainAdapter
import com.dicoding.footballclub.list.MainView
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.util.Types
import com.dicoding.footballclub.view.fragment.FavoriteFragment
import com.dicoding.footballclub.view.fragment.FootBallMatchFragment
import com.dicoding.footballclub.view.fragment.FootBallTeamFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity(), MainView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var bottomTabBar : BottomNavigationView
    private lateinit var changeLayout : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            changeLayout = frameLayout {
                lparams(width = matchParent, height = matchParent)

                {

                }
                id = R.id.changeLayout
            }

            relativeLayout {
                lparams(width = matchParent, height = wrapContent) {
                    alignParentBottom()
                }
                bottomTabBar = bottomNavigationView() {
                    inflateMenu(R.menu.main_menu)

                    lparams(width = matchParent, height = wrapContent)
                    {
                        backgroundColor = R.color.white
                        backgroundColorResource = R.color.white
                        gravity = Gravity.BOTTOM

                    }
                    itemBackgroundResource = R.color.white
                    selectedItemId = R.id.matches

                }
            }

        }

        bottomTabBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                bottomTabBar.selectedItemId -> return@setOnNavigationItemSelectedListener false
                R.id.matches -> {
                    setUpFragment(FootBallMatchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.teams -> {
                    setUpFragment(FootBallTeamFragment.newInstance(Types.LEAGUE))
                    return@setOnNavigationItemSelectedListener true

                }
                R.id.favorites -> {
                    setUpFragment(FavoriteFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        setUpFragment(FootBallMatchFragment())
    }

    private fun setUpFragment(fragment: Fragment){
        with(supportFragmentManager.beginTransaction()){
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            replace(R.id.changeLayout, fragment)
            commit()
        }
    }



    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }


    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

}
