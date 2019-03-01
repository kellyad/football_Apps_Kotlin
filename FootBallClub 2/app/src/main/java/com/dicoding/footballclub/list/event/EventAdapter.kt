package com.dicoding.footballclub.list.Event

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.dicoding.footballclub.R
import com.dicoding.footballclub.R.id.*
import com.dicoding.footballclub.model.Event
import com.dicoding.footballclub.util.DateTime
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class EventAdapter(private val events: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size

}

class EventUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL

                textView {
                    id = R.id.event_date
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        lparams(width = 0, height = wrapContent)
                        { weight = 3F
                          leftMargin = dip(20)
                        orientation = LinearLayout.VERTICAL}
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.region_name
                            textSize = 16f
                        }.lparams {
                            margin = dip(5)
                            width = wrapContent
                        }
                    }
                    linearLayout {
                        lparams(width = 0, height = wrapContent)
                        { weight = 0.5F}
                        gravity = Gravity.CENTER_HORIZONTAL

                        textView {
                            id = R.id.region_score
                            textSize = 16f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            margin = dip(5)
                            width = wrapContent
                        }
                    }
                    linearLayout {
                        lparams(width = 0, height = wrapContent)
                        { weight = 1F}
                        gravity = Gravity.CENTER_HORIZONTAL

                        textView {
                            id = R.id.versus_name
                            textSize = 16f
                        }.lparams {
                            margin = dip(5)
                            width = wrapContent
                        }
                    }
                    linearLayout {
                        lparams(width = 0, height = wrapContent)
                        { weight = 0.5F }
                        gravity = Gravity.CENTER_HORIZONTAL

                        textView {
                            id = R.id.versus_region_score
                            textSize = 16f
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            margin = dip(5)
                            width = wrapContent


                        }
                    }

                    linearLayout {
                        lparams(width = 0, height = wrapContent)
                        { weight = 3F
                          rightMargin = dip(20)}

                        gravity = Gravity.CENTER_HORIZONTAL
                        textView {
                            id = R.id.versus_region_name
                            textSize = 16f
                        }.lparams {
                            margin = dip(5)
                            width = wrapContent
                        }
                    }
                }

            }
        }
    }

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val eventDate: TextView = view.find(event_date)
    private val regionName: TextView = view.find(region_name)
    private val regionScore: TextView = view.find(region_score)
    private val versusName: TextView = view.find(versus_name)
    private val versusRegionName: TextView = view.find(versus_region_name)
    private val versusRegionScore: TextView = view.find(versus_region_score)

    fun bindItem(events: Event, listener: (Event) -> Unit) {
        eventDate.text = DateTime.getLongDate(events.dateEvent!!)
        regionName.text = events.strHomeTeam
        regionScore.text = events.intHomeScore
        versusName.text = "VS"
        versusRegionName.text = events.strAwayTeam
        versusRegionScore.text = events.intAwayScore

        itemView.onClick {
            //itemView.context.startActivity(itemView.context.intentFor<DetailActivity>("event" to events))
            listener(events)
        }
    }

}
