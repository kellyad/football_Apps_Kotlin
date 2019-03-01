package com.dicoding.footballclub.list.team.players.detail

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.*
import com.dicoding.footballclub.R
import com.dicoding.footballclub.list.team.TeamAdapter
import com.dicoding.footballclub.model.Player
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.search.match.FootBallMatchSearchPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*
import org.w3c.dom.Text

class PlayerDetailActivity: AppCompatActivity() {
    private lateinit var player: Player
    private lateinit var listAdapter: TeamAdapter

    private lateinit var presenter: FootBallMatchSearchPresenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var collapsingToolBar: CollapsingToolbarLayout
    private lateinit var params : AppBarLayout.LayoutParams
    private lateinit var  teamYear : TextView
    private lateinit var  teamImage : ImageView
    private lateinit var  teamStadium : TextView
    private lateinit var  teamName : TextView
    private lateinit var myTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager

    private lateinit var heightText : TextView
    private lateinit var widthText : TextView
    private lateinit var playerImage : ImageView
    private lateinit var playerHeight : TextView
    private lateinit var playerWeight : TextView
    private lateinit var playerPosition : TextView
    private lateinit var playerDescription : TextView

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        scrollView {
            lparams(width = matchParent, height = matchParent)
            linearLayout(){
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent, height = wrapContent)

                playerImage = imageView {
                    id = R.id.player_pic
                }.lparams{
                    width = matchParent
                    height = dip(200)
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    {
                        topMargin = dip(8)
                        orientation = LinearLayout.HORIZONTAL
                    }

                    heightText = textView {
                        id = R.id.height

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    widthText = textView {
                        id = R.id.width

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }
                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    {
                        orientation = LinearLayout.HORIZONTAL
                    }
                    playerHeight = textView {
                        id = R.id.player_height

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    playerWeight = textView {
                        id = R.id.player_weight

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    playerPosition = textView {
                        id = R.id.player_position

                    }.lparams {
                        leftMargin = dip(5)
                        width = matchParent
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                    playerDescription = textView {
                        id = R.id.player_description

                    }.lparams {
                        leftMargin = dip(5)
                        width = matchParent
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }
            }
        }
        player = intent.getParcelableExtra("player")

        heightText.text = "Height(m)"
        widthText.text = "Weight(Kg)"

        playerDescription.text = player.strDescriptionEN
        playerHeight.text = player.strHeight
        playerWeight.text = player.strWeight
        playerPosition.text = player.strPosition

        if(!player.strFanart1.equals(null)  && !player.strFanart1.equals("")) {
            Picasso.get().load(player.strFanart1).into(playerImage)
        }


    }
}