

package com.kelly.footballmatch.list.team.players


import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.kelly.footballmatch.R
import com.kelly.footballmatch.model.Player
import com.squareup.picasso.Picasso

import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.themedCardView
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

}

class PlayerUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

              themedCardView(R.style.AppTheme_NoActionBar) {
                    lparams {
                        height = wrapContent
                        width = matchParent
                        verticalMargin = dip(5)
                        horizontalMargin = dip(10)
                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL

                            linearLayout {
                                lparams(width = 0, height = wrapContent)
                                {
                                    weight = 1F
                                    orientation = LinearLayout.HORIZONTAL
                                }
                                gravity = Gravity.CENTER

                                imageView {
                                    id = R.id.playerPic
                                }.lparams {
                                    width = dip(50)
                                    height = dip(50)
                                }

                                textView {
                                    id = R.id.playerName
                                    this.gravity = Gravity.LEFT
                                }.lparams {
                                    leftMargin = dip(15)
                                    width = wrapContent
                                    height = wrapContent
                                    weight = 1F

                                }

                                textView {
                                    id = R.id.playerPosition
                                    this.gravity = Gravity.END
                                }.lparams {
                                    leftMargin = dip(5)
                                    rightMargin = dip(5)
                                    weight = 1F
                                    width = wrapContent
                                    height = wrapContent
                                }
                            }

                        }
                    }
                }
            }
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerImage: ImageView = view.find(R.id.playerPic)
    private val playerName: TextView = view.find(R.id.playerName)
    private val playerLoved: TextView = view.find(R.id.playerPosition)

    fun bindItem(players: Player, listener: (Player) -> Unit) {
        playerName.text = players.strPlayer
        playerLoved.text = "loved by "+ players.intLoved + " People"

        if(!players.strCutout.equals(null)  && !players.strCutout.equals("")){
            Picasso.get()
                    .load(players.strCutout)
                    .into(playerImage)
        }


        itemView.onClick {
            listener(players)
        }
    }

}