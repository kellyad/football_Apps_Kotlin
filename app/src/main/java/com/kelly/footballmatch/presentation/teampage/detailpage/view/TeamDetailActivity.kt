package com.kelly.footballmatch.presentation.teampage.detailpage.view

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.kelly.footballmatch.external.adapter.pageradapter.TeamPagerAdapter

import com.kelly.footballmatch.R
import com.kelly.footballmatch.data.responses.teams.Team
import com.kelly.footballmatch.data.network.service.LocalRepositoryApi

import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.RequiresApi
import org.jetbrains.anko.custom.async
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class TeamDetailActivity: AppCompatActivity() {

    private var isFavorite  : Boolean = false
    private var menuItem    : Menu? = null

    private lateinit var team               : Team
    private lateinit var collapsingToolBar  : Toolbar
    private lateinit var params             : AppBarLayout.LayoutParams

    private lateinit var teamImage          : ImageView
    private lateinit var myTabLayout        : TabLayout
    private lateinit var teamYear           : TextView
    private lateinit var teamStadium        : TextView
    private lateinit var teamName           : TextView
    private lateinit var myViewPager        : ViewPager
    private lateinit var teamFanArt        : LinearLayout

    private lateinit var localRepo          : LocalRepositoryApi

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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
         localRepo = LocalRepositoryApi(applicationContext)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        coordinatorLayout {
            appBarLayout {

                lparams(width = matchParent, height = wrapContent)
                fitsSystemWindows = true
                collapsingToolBar = toolbar().lparams(width = matchParent, height = wrapContent){
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                    fitsSystemWindows = true

                    teamFanArt = linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        {
                            orientation = LinearLayout.VERTICAL
                            //bottomMargin = actionBar.height
                            gravity = Gravity.CENTER
                            id = R.id.team_fanart

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

                    }

                }

                myTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = R.id.teamdetailtablayout
                    lparams(matchParent, wrapContent)
                    {
                        setSelectedTabIndicatorColor(resources.getColor(R.color.white))
                        gravity = Gravity.FILL
                        tabMode = TabLayout.MODE_FIXED

                    }
                }
            }

            myViewPager = viewPager {
                id = R.id.viewpager
            }.lparams(matchParent, matchParent)
        }
        initData()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initData(){

        team = intent.getParcelableExtra("team")
        // setting title header
        supportActionBar?.setTitle(team.strTeam)

        if(team.strTeamBadge != null  && team.strTeamBadge != ""){
            Picasso.get().load(team.strTeamBadge).into(teamImage)
        }

        teamName.text = team.strTeam
        teamYear.text = team.intFormedYear
        teamStadium.text = team.strStadium

        collapsingToolBar.setTitle(team.strTeam + " - " + team.strStadium)
        var bitmap :Bitmap? = null

        // test FanArtData before showing to imageView
        async {
            try {
                val url = URL(team.strTeamFanart1)
                val connection = url.openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                val input = connection.getInputStream()
                bitmap = BitmapFactory.decodeStream(input)
                if (bitmap !== null) {
                    val background = BitmapDrawable(resources, bitmap)
                    teamFanArt.setBackgroundDrawable(background)

                }

            } catch (e: IOException) {
                bitmap = null
            }
        }

        (myViewPager.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
        myViewPager.adapter = TeamPagerAdapter(supportFragmentManager, arrayListOf(team.strDescriptionEN.toString(), team.strTeam.toString()))
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