package com.kelly.footballmatch.presentation.eventpage.detailpage.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.kelly.footballmatch.R
import com.kelly.footballmatch.presentation.teampage.detailpage.view.TeamDetailActivity
import com.kelly.footballmatch.data.responses.events.Event
import com.kelly.footballmatch.data.responses.teams.Team
import com.kelly.footballmatch.presentation.eventpage.detailpage.presenter.DetailPresenter
import com.kelly.footballmatch.presentation.eventpage.detailpage.contract.DetailView
import com.kelly.footballmatch.external.util.DateTime
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), DetailView.View {
    //val presenter  : DetailPresenter by inject ()

    @Inject
    lateinit var presenter: DetailPresenter
    private lateinit var event  : Event
    private var menuItem        : Menu? = null
    private var isFavorite      : Boolean = false
    private var isEvent         : Boolean = false

    private lateinit var roundTv        : TextView
    private lateinit var resultTv        : TextView
    private lateinit var dateScheduleTv : TextView
    private lateinit var timeScheduleTv : TextView

    private lateinit var redCardsHomeUnitTv     : TextView
    private lateinit var redCardsAwayUnitTv     : TextView
    private lateinit var yellowCardsHomeUnitTv  : TextView
    private lateinit var yellowCardsAwayUnitTv  : TextView

    private lateinit var homeNameTv     : TextView
    private lateinit var homeScoreTv    : TextView
    private lateinit var awayNameTv     : TextView
    private lateinit var awayScoreTv    : TextView

    private lateinit var homeScorerTv   : TextView
    private lateinit var awayScorerTv   : TextView

    private lateinit var gkHomeTv       : TextView
    private lateinit var gkAwayTv       : TextView

    private lateinit var defHomeTv      : TextView
    private lateinit var defAwayTv      : TextView

    private lateinit var midHomeTv      : TextView
    private lateinit var midAwayTv      : TextView

    private lateinit var forHomeTv      : TextView
    private lateinit var forAwayTv      : TextView

    private lateinit var subHomeTv      : TextView
    private lateinit var subAwayTv      : TextView

    private lateinit var shHomeTv       : TextView
    private lateinit var shAwayTv       : TextView

    private lateinit var homeFormationTv: TextView
    private lateinit var awayFormationTv: TextView

    private lateinit var homeImg        : ImageView
    private lateinit var awayImg        : ImageView

    private lateinit var homeTeamButton : Button
    private lateinit var awayTeamButton : Button
    private lateinit var buttonLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scrollView {
            lparams(width = matchParent , height = matchParent)
            linearLayout {
                lparams(width = matchParent , height = wrapContent)
                orientation = LinearLayout.VERTICAL
                padding = dip(9)
                relativeLayout {
                    lparams(width = matchParent , height = wrapContent)
                    cardView {
                        lparams(width = matchParent , height = wrapContent)
                        linearLayout {
                            lparams(width = matchParent , height = wrapContent)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL
                            dateScheduleTv = textView{
                                id = R.id.dateScheduleTv
                                setTextColor(resources.getColor(R.color.pass_match))
                            }.lparams{
                                width = wrapContent
                                height = wrapContent
                            }

                            timeScheduleTv = textView{
                                id = R.id.timeScheduleTv
                                setTextColor(resources.getColor(R.color.pass_match))
                            }.lparams{
                                width = wrapContent
                                height = wrapContent
                            }
                        }

                        linearLayout {
                            lparams(width = matchParent , height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL

                            verticalLayout {
                                lparams(width = dip(0) , height = wrapContent){
                                    weight = 3f
                                    orientation = LinearLayout.VERTICAL
                                    leftMargin = dip(20)
                                    topMargin = dip(20)
                                    gravity = Gravity.CENTER
                                    homeImg = imageView {
                                        id = R.id.homeImg
                                    }.lparams(width = dip(80), height = dip(80)){

                                        gravity = Gravity.CENTER
                                    }

                                    homeNameTv = textView{
                                        id = R.id.homeNameTv
                                        setTextAppearance(ctx, R.style.ClubText)
                                        topMargin = dip(40)
                                        text = "Liver Pool"
                                        gravity = Gravity.CENTER

                                    }.lparams{
                                        width = wrapContent
                                        height = wrapContent
                                        gravity = Gravity.CENTER
                                    }

                                    homeFormationTv = textView{
                                        id = R.id.homeFormationTv
                                        textSize = 13f
                                        topMargin = dip(40)
                                        gravity = Gravity.CENTER
                                        text = "0-0-0"

                                    }.lparams{
                                        width = wrapContent
                                        height = wrapContent
                                        gravity = Gravity.CENTER
                                    }
                                }

                            }
                            linearLayout {
                                lparams(width = dip(0), height = wrapContent)
                                {
                                    weight = 2f
                                    gravity = Gravity.CENTER
                                    orientation = LinearLayout.VERTICAL
                                }
                                roundTv = textView {
                                    id = R.id.roundTv
                                    textSize = 33f
                                    gravity = Gravity.CENTER
                                    setTextAppearance(ctx, R.style.ClubText)
                                    text = "0"
                                }.lparams {
                                    width = wrapContent
                                    height = wrapContent
                                    gravity = Gravity.CENTER
                                }

                                resultTv = textView {
                                    id = R.id.resultTv
                                    gravity = Gravity.CENTER
                                    setTextAppearance(ctx, R.style.ClubText)
                                    text = "0"
                                }.lparams {
                                    width = wrapContent
                                    height = wrapContent
                                    gravity = Gravity.CENTER
                                }
                                linearLayout {
                                    lparams(width = matchParent, height = wrapContent)
                                    {
                                        gravity = Gravity.CENTER
                                        orientation = LinearLayout.HORIZONTAL
                                    }
                                    linearLayout {
                                        lparams(width = dip(0), height = wrapContent)
                                        {
                                            weight = 0.5f
                                            gravity = Gravity.CENTER
                                            orientation = LinearLayout.VERTICAL
                                        }
                                        homeScoreTv = textView {
                                            id = R.id.homeScoreTv
                                            textSize = 33f
                                            gravity = Gravity.CENTER
                                            setTextAppearance(ctx, R.style.ClubText)
                                            text = "0"
                                        }.lparams {
                                            width = wrapContent
                                            height = wrapContent
                                            gravity = Gravity.CENTER
                                        }

                                    }

                                    linearLayout {
                                        lparams(width = dip(0), height = wrapContent)
                                        {
                                            weight = 1f
                                            gravity = Gravity.CENTER
                                            orientation = LinearLayout.VERTICAL
                                        }
                                        textView {
                                            // setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display3)
                                            gravity = Gravity.CENTER
                                            text = "VS"
                                        }.lparams {
                                            width = wrapContent
                                            height = wrapContent
                                            gravity = Gravity.CENTER
                                        }

                                    }

                                    linearLayout {
                                        lparams(width = dip(0), height = wrapContent)
                                        {
                                            weight = 0.5f
                                            gravity = Gravity.CENTER
                                            orientation = LinearLayout.VERTICAL
                                        }
                                        awayScoreTv = textView {
                                            id = R.id.awayScoreTv
                                            textSize = 33f
                                            setTextAppearance(ctx, R.style.ClubText)
                                            gravity = Gravity.CENTER
                                            text = "0"
                                        }.lparams {
                                            width = wrapContent
                                            height = wrapContent
                                            gravity = Gravity.CENTER
                                        }
                                    }
                                }
                            }
                            verticalLayout {
                                lparams(width = dip(0) , height = wrapContent){
                                    weight = 3f
                                    leftMargin = dip(20)
                                    topMargin = dip(20)
                                    awayImg = imageView {
                                        id = R.id.awayImg
                                        gravity = Gravity.CENTER
                                    }.lparams(width = dip(80), height = dip(80)){

                                        gravity = Gravity.CENTER
                                    }

                                    awayNameTv = textView{
                                        id = R.id.awayNameTv
                                        setTextAppearance(ctx, R.style.ClubText)
                                        topMargin = dip(40)
                                        gravity = Gravity.CENTER
                                        text = "Liver Pool"

                                    }.lparams{
                                        width = wrapContent
                                        height = wrapContent
                                        gravity = Gravity.CENTER
                                    }

                                    awayFormationTv = textView{
                                        id = R.id.awayFormationTv
                                        textSize = 13f
                                        topMargin = dip(40)
                                        gravity = Gravity.CENTER_HORIZONTAL
                                        text = "0-0-0"
                                        gravity = Gravity.CENTER

                                    }.lparams{
                                        width = wrapContent
                                        height = wrapContent
                                        gravity = Gravity.CENTER
                                    }
                                }

                            }

                        }
                    }
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    homeScorerTv = textView{
                        id = R.id.homeScorerTv
                        textSize = 12f
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                      //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Goals"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    awayScorerTv = textView{
                        id = R.id.awayScorerTv
                        textSize = 12f
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    shHomeTv = textView{
                        id = R.id.shHomeTv
                        text = "0"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        textSize = 13f
                      //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Shots"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    shAwayTv = textView{
                        id = R.id.shAwayTv
                        text = "0"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    redCardsHomeUnitTv = textView{
                        id = R.id.redCardsHomeUnitTv
                        textSize = 12f
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Red Cards"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    redCardsAwayUnitTv = textView{
                        id = R.id.redCardsAwayUnitTv
                        textSize = 12f
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    yellowCardsHomeUnitTv = textView{
                        id = R.id.yellowCardsHomeUnitTv
                        textSize = 12f
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Yellow Cards"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    yellowCardsAwayUnitTv = textView{
                        id = R.id.yellowCardsAwayUnitTv
                        textSize = 12f
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }
                view{
                    setBackgroundColor(Color.BLACK)

                }.lparams(width= matchParent, height = dip(1)){
                    horizontalMargin = dip(5)
                }

                textView{
                    text = "Lineups"
                  //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display3)
                }.lparams{
                    width = wrapContent
                    height = wrapContent
                    weight = 2f
                    gravity = Gravity.CENTER
                }

                view{
                    setBackgroundColor(Color.BLACK)

                }.lparams(width= matchParent, height = dip(1)){
                    horizontalMargin = dip(5)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    gkHomeTv = textView{
                        id = R.id.gkHomeTv
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        textSize = 13f
                      //  setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Goal Keeper"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    gkAwayTv = textView{
                        id = R.id.gkAwayTv
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    defHomeTv = textView{
                        id = R.id.defHomeTv
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        textSize = 13f
                     //   setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Defense"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    defAwayTv = textView{
                        id = R.id.defAwayTv
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    midHomeTv = textView{
                        id = R.id.midHomeTv
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        textSize = 13f
                     //   setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Midfield"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    midAwayTv = textView{
                        id = R.id.midAwayTv
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    forHomeTv = textView{
                        id = R.id.forHomeTv
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    textView{
                        textSize = 13f
                    //    setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display2)
                        text = "Forward"
                        gravity= Gravity.CENTER
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 1f
                        gravity= Gravity.CENTER
                    }

                    forAwayTv = textView{
                        id = R.id.forAwayTv
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity= Gravity.RIGHT
                    }
                }

                view{
                    setBackgroundColor(Color.BLACK)

                }.lparams(width= matchParent, height = dip(1)){
                    horizontalMargin = dip(5)
                }

                textView{
                    text = "Substitutes"
                    gravity= Gravity.CENTER
                //    setTextAppearance(ctx,R.style.TextAppearance_AppCompat_Display3)
                }.lparams{
                    width = wrapContent
                    height = wrapContent
                    weight = 2f
                    gravity = Gravity.CENTER
                }

                view{
                    setBackgroundColor(Color.BLACK)

                }.lparams(width= matchParent, height = dip(1)){
                    horizontalMargin = dip(5)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    subHomeTv = textView{
                        id = R.id.subHomeTv
                        text = "Christy"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    subAwayTv = textView{
                        id = R.id.subAwayTv
                        text = "Christy"
                        gravity = Gravity.RIGHT
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity = Gravity.RIGHT
                    }
                }
                buttonLayout = linearLayout {
                    lparams(width = matchParent, height = wrapContent){
                        orientation = LinearLayout.HORIZONTAL
                    }
                    homeTeamButton = button{
                        id = R.id.HomeTeamBt
                        text = "View Team"
                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                    }
                    awayTeamButton = button{
                        id = R.id.AwayTeamBt
                        text = "View Team"
                        gravity = Gravity.CENTER

                    }.lparams{
                        width = dip(0)
                        height = wrapContent
                        weight = 2f
                        gravity = Gravity.RIGHT
                    }
                }
            }
        }
//        presenter.onAttachedView(this)
        initData()

    }
    override fun initData(){
        AndroidInjection.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        isEvent = intent.hasExtra("event")

        if(isEvent) {
            event = intent.getParcelableExtra<Event>("event")
            presenter.showDataTeam(event)
            presenter.checkMatchFromDB(event.idEvent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite){
                    if(isEvent) {
                        presenter.insertMatchFromDB(event)
                    }

                    toast("Event added to favorite")
                    isFavorite = !isFavorite
                }else{
                    if(isEvent) {
                        presenter.deleteMatchFromDB(event.idEvent)
                    }
                    toast("Event removed favorite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    override fun showDataTeam(event: Event?) {
        if(event != null) {
            supportActionBar?.title = event.strEvent
            val Format = SimpleDateFormat("yyyy-MM-dd")

            val TimeFormat = SimpleDateFormat("HH:mm")
            val dateGMTFormat = DateTime.changeToGMTFormat(event.dateEvent, event.strTime)

            dateScheduleTv.text = DateTime.getLongDate(Format.format(dateGMTFormat))
            timeScheduleTv.text = TimeFormat.format(dateGMTFormat)

            roundTv.text = "Round " + event.intRound

            resultTv.text = presenter.calculateWinningResult(event.intHomeScore,event.intAwayScore)

            homeNameTv.text = event.strHomeTeam
            homeScoreTv.text = event.intHomeScore
            awayNameTv.text = event.strAwayTeam
            awayScoreTv.text = event.intAwayScore

            redCardsAwayUnitTv.text = event.strAwayRedCards
            redCardsHomeUnitTv.text = event.strHomeRedCards
            yellowCardsAwayUnitTv.text = event.strAwayYellowCards
            yellowCardsHomeUnitTv.text = event.strHomeYellowCards

            homeScorerTv.text = event.strHomeGoalDetails
            awayScorerTv.text = event.strAwayGoalDetails

            gkHomeTv.text = event.strHomeLineupGoalkeeper
            gkAwayTv.text = event.strAwayLineupGoalkeeper

            defHomeTv.text = event.strHomeLineupDefense
            defAwayTv.text = event.strAwayLineupDefense

            midHomeTv.text = event.strHomeLineupMidfield
            midAwayTv.text = event.strAwayLineupMidfield

            forHomeTv.text = event.strHomeLineupForward
            forAwayTv.text = event.strAwayLineupForward

            subHomeTv.text = event.strHomeLineupSubstitutes
            subAwayTv.text = event.strAwayLineupSubstitutes

            shHomeTv.text = event.intHomeShots
            shAwayTv.text = event.intAwayShots

            homeFormationTv.text = event.strHomeFormation
            awayFormationTv.text = event.strAwayFormation

            if(event.strHomeBadge != null && event.strHomeBadge != "") {
                Picasso.get().load(event.strHomeBadge).into(homeImg)
            }
            if(event.strAwayBadge != null  && event.strAwayBadge != "") {
                Picasso.get().load(event.strAwayBadge).into(awayImg)
            }
            if(event.idHomeTeam == null || event.idAwayTeam == null){
                buttonLayout.visibility = View.INVISIBLE

            }
            homeTeamButton.setOnClickListener{
                presenter.goToDetailActivity(event.idHomeTeam)
            }
            awayTeamButton.setOnClickListener{
                presenter.goToDetailActivity(event.idAwayTeam)
            }
        }

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_fav)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_fav)
    }

    override fun setFavoriteState(favList: List<Event>) {
        if(!favList.isEmpty()) isFavorite = true
    }

    override fun goToDetailActivity(team: Team) {
        ctx.startActivity<TeamDetailActivity>("team" to team)
    }



}