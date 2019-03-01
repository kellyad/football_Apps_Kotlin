package com.kelly.footballmatch.list.team

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kelly.footballmatch.R
import com.kelly.footballmatch.model.Team
import com.kelly.footballmatch.util.DateTime
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.cardview.v7.themedCardView
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            themedCardView(R.style.AppTheme_NoActionBar) {
                lparams {
                    height = wrapContent
                    width = matchParent
                    verticalMargin = dip(5)
                    horizontalMargin = dip(10)}

                linearLayout {
                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL

                    verticalLayout {
                        orientation = LinearLayout.HORIZONTAL
                        padding = dip(16)

                        imageView {
                            id = R.id.imageView
                        }.lparams(dip(50), dip(50))

                        linearLayout() {
                            lparams(width= dip(0), height = wrapContent)
                            {
                                orientation = LinearLayout.VERTICAL
                                weight = 2.5f
                            }

                            textView {
                                id = R.id.textView
                            }.lparams(wrapContent, wrapContent) {
                                gravity = Gravity.CENTER_VERTICAL
                                leftMargin = dip(16)
                            }
                            textView {
                                id = R.id.teamDescription
                            }.lparams(wrapContent, wrapContent) {
                                gravity = Gravity.CENTER_VERTICAL
                                leftMargin = dip(16)
                            }

                            textView {
                                id = R.id.teamPlace
                            }.lparams(wrapContent, wrapContent) {
                                gravity = Gravity.CENTER_VERTICAL
                                leftMargin = dip(16)
                            }
                        }
                        linearLayout() {
                            lparams(width= dip(0), height = wrapContent)
                            {
                                orientation = LinearLayout.VERTICAL
                                weight = 1f
                            }
                            textView {
                                text = "Jersey :"
                                textSize = 15f
                            }.lparams(wrapContent, wrapContent) {
                                gravity = Gravity.CENTER_VERTICAL
                                leftMargin = dip(16)
                            }
                            imageView {
                                id = R.id.teamJersey

                            }.lparams(dip(50), dip(50)){
                                gravity= Gravity.CENTER
                            }
                        }
                    }
                }
            }
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamImage: ImageView = view.findViewById(R.id.imageView)
    private val teamName: TextView = view.findViewById(R.id.textView)
    private val teamDescription: TextView = view.findViewById(R.id.teamDescription)
    private val teamPlace: TextView = view.findViewById(R.id.teamPlace)
    private val teamJersey: ImageView = view.findViewById(R.id.teamJersey)



    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        if(!teams.strTeamBadge.equals(null)  && !teams.strTeamBadge.equals("")) {
            Picasso.get().load(teams.strTeamBadge)
                    .into(teamImage)
        }
        teamName.text = teams.strTeam
        teamDescription.text = teams.strAlternate
        teamPlace.text = "addressed in "+ teams.strStadium + "\n on Country " + teams.strCountry

        if(!teams.strTeamJersey.equals(null) && !teams.strTeamJersey.equals("") ) {
             Picasso.get().load(teams.strTeamJersey)
                    .into(teamJersey)
        }

        itemView.onClick {
            //itemView.context.startActivity(itemView.context.intentFor<DetailActivity>("event" to events))
            listener(teams)
        }
    }

}