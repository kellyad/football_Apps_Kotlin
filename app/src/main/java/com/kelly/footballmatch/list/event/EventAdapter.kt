package com.kelly.footballmatch.list.Event

import android.content.Intent
import android.graphics.Typeface
import android.provider.CalendarContract
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.kelly.footballmatch.R
import com.kelly.footballmatch.R.id.*
import com.kelly.footballmatch.model.Event
import com.kelly.footballmatch.util.DateTime
import com.squareup.picasso.Picasso

import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.themedCardView
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat


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
            themedCardView(R.style.AppTheme_NoActionBar) {
                lparams {
                    height = wrapContent
                    width = matchParent
                    verticalMargin = dip(5)
                    horizontalMargin = dip(10)}

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        textView {
                            id = R.id.event_date
                            textSize = 16f
                        }.lparams {
                            topMargin = dip(10)
                            bottomMargin = dip(5)
                        }

                        floatingActionButton {
                            imageResource = R.drawable.ic_event_black_24dp

                            id = R.id.add_To_Calendar
                        }.lparams(width= matchParent, height = wrapContent){
                            gravity = Gravity.END


                        }

                    }

                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.HORIZONTAL

                        linearLayout {
                            lparams(width = 0, height = wrapContent)
                            {
                                weight = 3F
                                leftMargin = dip(20)
                                orientation = LinearLayout.VERTICAL
                            }
                            gravity = Gravity.CENTER
                            imageView {
                                id = R.id.homeImg
                            }.lparams(width = dip(50), height = dip(50)){

                                gravity = Gravity.CENTER
                            }

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
                            { orientation = LinearLayout.VERTICAL
                            weight = 2F}
                            gravity = Gravity.CENTER_HORIZONTAL

                            textView {
                                id = R.id.event_time
                                textSize = 16f
                            }.lparams {
                            }

                            linearLayout {
                                lparams(width = matchParent, height = matchParent)
                                { orientation = LinearLayout.HORIZONTAL }
                                linearLayout {
                                    lparams(width = 0, height = wrapContent)
                                    { orientation = LinearLayout.VERTICAL
                                        weight = 0.5F }
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
                                    {
                                        weight = 1F
                                        orientation = LinearLayout.VERTICAL
                                    }
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
                            }
                        }

                        linearLayout {
                            lparams(width = 0, height = wrapContent)
                            {
                                weight = 3F
                                rightMargin = dip(20)
                                orientation = LinearLayout.VERTICAL
                            }

                            gravity = Gravity.CENTER_HORIZONTAL
                            imageView {
                                id = R.id.awayImg
                            }.lparams(width = dip(50), height = dip(50)){

                                gravity = Gravity.CENTER
                            }

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

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val eventDate: TextView = view.find(event_date)
    private val eventTime: TextView = view.find(event_time)
    private val addToCalendar: FloatingActionButton = view.find(add_To_Calendar)
    private val regionName: TextView = view.find(region_name)
    private val regionScore: TextView = view.find(region_score)
    private val versusName: TextView = view.find(versus_name)
    private val versusRegionName: TextView = view.find(versus_region_name)
    private val versusRegionScore: TextView = view.find(versus_region_score)
    private val homeImage: ImageView = view.find(homeImg)
    private val awayImage: ImageView = view.find(awayImg)


    fun bindItem(events: Event, listener: (Event) -> Unit) {

        regionName.text = events.strHomeTeam
        regionScore.text = events.intHomeScore
        versusName.text = "VS"
        versusRegionName.text = events.strAwayTeam
        versusRegionScore.text = events.intAwayScore

        if(!events.strHomeBadge.equals(null) && !events.strHomeBadge.equals("")) {
            Picasso.get().load(events.strHomeBadge).into(homeImage)
        }
        if(!events.strAwayBadge.equals(null) && !events.strAwayBadge.equals("")) {
            Picasso.get().load(events.strAwayBadge).into(awayImage)
        }
        itemView.onClick {
            listener(events)
        }
        val DateNow = System.currentTimeMillis()
        val Format = SimpleDateFormat("yyyy-MM-dd")

        val TimeFormat = SimpleDateFormat("HH:mm")
        val DateFormated = Format.format(DateNow)
        val DifferentDate = events.dateEvent!!.compareTo(DateFormated)
        val dateGMTFormat = DateTime.changeToGMTFormat(events.dateEvent, events.strTime)

        eventDate.text = DateTime.getLongDate(Format.format(dateGMTFormat))
        eventTime.text = TimeFormat.format(dateGMTFormat)

        when {
            !DifferentDate.equals(null) && DifferentDate >= 0 -> {
                addToCalendar.visibility = View.VISIBLE
                addToCalendar.setOnClickListener {
                    val intent = Intent(Intent.ACTION_EDIT)
                    intent.type = "vnd.android.cursor.item/event"
                    intent.putExtra(CalendarContract.Events.TITLE, events.strEvent)
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateGMTFormat)
                    intent.putExtra(CalendarContract.Events.ALL_DAY, false)
                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateGMTFormat)
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, events.strEvent)
                    itemView.context.startActivity(intent)
                }
            }
            else -> {
                addToCalendar.visibility = View.INVISIBLE
            }
        }
    }

}
