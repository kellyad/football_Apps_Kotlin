package com.kelly.footballmatch.list.team.players.detail

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.widget.*
import com.kelly.footballmatch.Adapter.ImageProfileAdapter

import com.kelly.footballmatch.R
import com.kelly.footballmatch.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class PlayerDetailActivity: AppCompatActivity() {

    private lateinit var player             : Player

    private lateinit var playerImage        : ImageView
    private lateinit var playerName         : TextView
    private lateinit var playerNationality  : TextView
    private lateinit var playerBirthDate    : TextView
    private lateinit var playerSigning      : TextView
    private lateinit var heightText         : TextView
    private lateinit var widthText          : TextView
    private lateinit var playerHeight       : TextView
    private lateinit var playerWeight       : TextView
    private lateinit var playerPosition     : TextView
    private lateinit var playerDescription  : TextView
    private lateinit var myViewPager  : ViewPager
    private lateinit var screenAdapter : PagerAdapter

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

            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(width = matchParent, height = wrapContent)

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    myViewPager = viewPager {
                        id = R.id.viewpager
                    }.lparams(width = matchParent, height = dip(200))
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    {
                        topMargin = dip(8)
                        orientation = LinearLayout.HORIZONTAL
                    }

                    textView {
                        text = "Birth Date : "
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)
                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    playerBirthDate = textView {

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    {
                        topMargin = dip(8)
                        orientation = LinearLayout.HORIZONTAL
                    }

                    textView {
                        text = "Player Signing : "
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)
                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    playerSigning = textView {

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }


                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    {
                        topMargin = dip(8)
                        orientation = LinearLayout.HORIZONTAL
                    }

                    textView {
                        text = "Nationality : "
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)
                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    playerNationality = textView {

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                    view{
                        setBackgroundColor(Color.BLACK)

                    }.lparams(width= matchParent, height = dip(1))
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    {
                        topMargin = dip(8)
                        orientation = LinearLayout.HORIZONTAL
                    }

                    heightText = textView {
                        id = R.id.height
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    widthText = textView {
                        id = R.id.width
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)

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
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Display3)

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }

                    playerWeight = textView {
                        id = R.id.player_weight
                        setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Display3)

                    }.lparams{
                        leftMargin = dip(5)
                        width = dip(0)
                        height = wrapContent
                        weight = 1.0.toFloat()

                    }
                }
                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        gravity = Gravity.CENTER_HORIZONTAL

                        playerPosition = textView {
                            id = R.id.player_position
                            setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Medium)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }.lparams {
                            leftMargin = dip(5)
                            width = matchParent
                            height = wrapContent
                            weight = 1.0.toFloat()

                        }
                    }
                    view{
                        setBackgroundColor(Color.BLACK)

                    }.lparams(width= matchParent, height = dip(1))

                    playerDescription = textView {
                        id = R.id.player_description
                    }.lparams {
                        topMargin = dip(5)
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

//        playerName.text = "Player Name is : " + player.strPlayer
        playerBirthDate.text =  player.strBirthLocation + ", " + player.dateBorn
        playerNationality.text =  player.strNationality
        playerSigning.text =  player.strSigning + " date on "+ player.dateSigned

        playerDescription.text = player.strDescriptionEN
        playerHeight.text = player.strHeight
        playerWeight.text = player.strWeight
        playerPosition.text = player.strPosition

        supportActionBar?.setTitle(player.strPlayer)

        var images: ArrayList<String> =    arrayListOf()
        if(!player.strFanart1.equals(null) && !player.strFanart1.equals("") ){
            images.add(player.strFanart1!!)
        }
        if(!player.strFanart2.equals(null) && !player.strFanart2.equals("") ){
            images.add(player.strFanart2!!)
        }
        if(!player.strFanart3.equals(null) && !player.strFanart3.equals("") ){
            images.add(player.strFanart3!!)
        }
        if(!player.strFanart4.equals(null) && !player.strFanart4.equals("") ){
            images.add(player.strFanart4!!)
        }

        screenAdapter = ImageProfileAdapter(applicationContext, images)
        myViewPager.adapter = screenAdapter
        //Picasso.get().load(player.strFanart1).into(playerImage)


    }
}