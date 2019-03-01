package com.dicoding.footballclub.list.team.detail

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.dicoding.footballclub.Adapter.ViewPagerAdapter
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.team.TeamAdapter
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.model.repository.LocalRepositoryImpl
import com.dicoding.footballclub.search.match.FootBallMatchSearchPresenter
import com.dicoding.footballclub.view.fragment.TeamOverViewFragment
import com.dicoding.footballclub.view.fragment.TeamPlayersFragment
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamDetailActivity: AppCompatActivity() {
    private lateinit var team: Team
    private lateinit var collapsingToolBar: CollapsingToolbarLayout
    private lateinit var params : AppBarLayout.LayoutParams
    private lateinit var  teamYear : TextView
    private lateinit var  teamImage : ImageView
    private lateinit var  teamStadium : TextView
    private lateinit var  teamName : TextView
    private lateinit var myTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    private lateinit var localRepo :LocalRepositoryImpl

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite){
                    insertFavoriteTeam(team)
                    toast("Event added to favorite")
                    isFavorite = !isFavorite
                }else{
                    deleteFavoriteTeam(team.idTeam)

                    toast("Event removed favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
         localRepo = LocalRepositoryImpl(applicationContext)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        coordinatorLayout {
            appBarLayout {
                lparams(width = matchParent, height = wrapContent)
                fitsSystemWindows = true
                setTheme(R.style.AppTheme_AppBarOverlay)
                collapsingToolBar = collapsingToolbarLayout {
                    lparams(width = matchParent, height = wrapContent)
                    fitsSystemWindows = true
                    isTitleEnabled = false
                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))
                    scrollView {
                        lparams(width = matchParent, height = wrapContent)
                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            {
                                orientation = LinearLayout.VERTICAL
                                //bottomMargin = actionBar.height
                                gravity = Gravity.CENTER


                            }
                            teamImage = imageView {
                                id = R.id.team_pic
                            }.lparams {
                                height = dip(100)
                                width = matchParent
                                bottomMargin = dip(8)
                                contentDescription = "masuk"
                            }

                            teamName = textView {
                                id = R.id.team_name
                                textSize = 16f
                                setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Headline)
                                this.gravity = Gravity.CENTER
                            }.lparams {
                                leftMargin = dip(15)
                                height = wrapContent
                                width = matchParent


                            }

                            teamYear = textView {
                                id = R.id.team_year
                                setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Subhead)
                                this.gravity = Gravity.CENTER
                            }.lparams {
                                leftMargin = dip(15)
                                height = wrapContent
                                width = matchParent

                            }

                            teamStadium = textView {
                                id = R.id.team_stadium
                                setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Subhead)
                                this.gravity = Gravity.CENTER
                            }.lparams {
                                leftMargin = dip(15)
                                height = wrapContent
                                width = matchParent
                            }
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

                    }


                }

            }
            myViewPager = viewPager {
                id = R.id.viewpager
            }.lparams(matchParent, matchParent)
            (myViewPager?.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()

//            relativeLayout {
//                lparams(width = matchParent, height = matchParent)
//
//                swipeRefresh = swipeRefreshLayout {
//                    setColorSchemeResources(R.color.colorAccent,
//                            android.R.color.holo_green_light,
//                            android.R.color.holo_orange_light,
//                            android.R.color.holo_red_light)
//                    id = R.id.swipeRefresh
//                    frameLayout {
//                        lparams(width = matchParent, height = matchParent)
//
//                        listEvent = recyclerView {
//                            lparams(width = matchParent, height = wrapContent)
//                            layoutManager = LinearLayoutManager(ctx)
//                            id = R.id.recycler
//                        }
//
//                        progressBar = progressBar {
//                        }.lparams {
//                            //centerHorizontally()
//                            gravity = Gravity.CENTER
//                        }
//                    }
//                }
//            }
        }
            params = collapsingToolBar.getLayoutParams() as AppBarLayout.LayoutParams;
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
            collapsingToolBar.setLayoutParams(params);

            team = intent.getParcelableExtra("team")

            if(!team.strTeamBadge.equals(null)  && !team.strTeamBadge.equals("")){
                Picasso.get()
                        .load(team.strTeamBadge)
                        .into(teamImage)
            }

            teamName.text = team.strTeam
            teamYear.text = team.intFormedYear
            teamStadium.text = team.strStadium
            myViewPager.adapter = ViewPagerAdapter(supportFragmentManager,
                    mapOf(
                            getString(R.string.Overview) to TeamOverViewFragment.newInstance(team.strDescriptionEN.toString()),
                            getString(R.string.players) to TeamPlayersFragment.newInstance(team.strTeam.toString())
                    )
            )
        myTabLayout.setupWithViewPager(myViewPager)

        checkTeam(team.idTeam)

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav)
    }
    private fun deleteFavoriteTeam(id: String?) {
        localRepo.deleteFavoriteTeamData(id)
    }

    private fun checkTeam(id: String?) {
        setFavoriteState(localRepo.checkFavoriteTeam(id))
    }

    private fun insertFavoriteTeam(data: Team) {
        localRepo.insertFavoriteTeamData(data)
    }
    private fun setFavoriteState(favList: List<Team>) {
        if(!favList.isEmpty()) isFavorite = true
    }
}