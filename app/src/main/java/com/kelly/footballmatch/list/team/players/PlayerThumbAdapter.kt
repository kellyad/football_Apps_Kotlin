package com.kelly.footballmatch.list.team.players


import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.kelly.footballmatch.R
import com.kelly.footballmatch.R.id.*
import com.kelly.footballmatch.model.Player
import com.squareup.picasso.Picasso

import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.themedCardView
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerThumbAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerThumbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerThumbViewHolder {
        return PlayerThumbViewHolder(PlayerThumbUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerThumbViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

}

class PlayerThumbUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            themedCardView(R.style.AppTheme_NoActionBar) {
                lparams {
                    height = dip(170)
                    width = dip(170)
                    verticalMargin = dip(5)
                    horizontalMargin = dip(10)}

                linearLayout {
                    lparams(width = matchParent, height = matchParent)
                    orientation = LinearLayout.VERTICAL
                    id = R.id.image_layout


                            imageView {
                                id = R.id.player_pic
                            }.lparams {
                                width = dip(150)
                                height = dip(130)
                                gravity = Gravity.CENTER
                            }

                            textView {
                                id = R.id.player_name
                                setTextColor(Color.WHITE)
                            }.lparams {
                                gravity = Gravity.CENTER
                                width = wrapContent
                                height = wrapContent


                            }

                            textView {
                                id = R.id.player_position
                                setTextColor(Color.WHITE)
                            }.lparams {
                                gravity = Gravity.CENTER

                                width = wrapContent
                                height = wrapContent
                            }
                }
            }
        }
    }

}

class PlayerThumbViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerImage: ImageView = view.find(player_pic)
    private val playerName: TextView = view.find(player_name)
    private val playerPosition: TextView = view.find(player_position)
    private val imageLayout: LinearLayout = view.find(image_layout)
    private  var image : String? = null
    fun bindItem(players: Player, listener: (Player) -> Unit) {
        playerName.text = players.strPlayer
        playerPosition.text = players.strPosition
        imageLayout.setBackgroundColor(Color.BLACK)

        if(players.strCutout.equals(null) || players.strCutout.equals("")){
            image = players.strThumb
        }
        else {
            image = players.strCutout
        }
        if(!image.equals(null) && !image.equals("")) {
            Picasso.get()
                    .load(image)
                    .into(playerImage)
        }

        itemView.onClick {
            listener(players)
        }
    }

}