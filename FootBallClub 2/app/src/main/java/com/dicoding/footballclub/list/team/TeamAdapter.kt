package com.dicoding.footballclub.list.team

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.footballclub.R
import com.dicoding.footballclub.model.Team
import com.dicoding.footballclub.util.DateTime
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
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
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                verticalLayout {
                    orientation = LinearLayout.HORIZONTAL
                    padding = dip(16)

                    imageView {
                        id = R.id.imageView
                    }.lparams(dip(50), dip(50))

                    textView {
                        id = R.id.textView
                    }.lparams(wrapContent, wrapContent) {
                        gravity = Gravity.CENTER_VERTICAL
                        leftMargin = dip(16)
                    }
                }
            }
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamImage: ImageView = view.findViewById(R.id.imageView)
    private val teamName: TextView = view.findViewById(R.id.textView)


    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Log.d("data cek", teams.toString())
        if(!teams.strTeamBadge.equals(null) && !teams.strTeamBadge.equals("") ){
            Picasso.get()
                    .load(teams.strTeamBadge)
                    .into(teamImage)
        }

        teamName.text = teams.strTeam

        itemView.onClick {
            //itemView.context.startActivity(itemView.context.intentFor<DetailActivity>("event" to events))
            listener(teams)
        }
    }

}