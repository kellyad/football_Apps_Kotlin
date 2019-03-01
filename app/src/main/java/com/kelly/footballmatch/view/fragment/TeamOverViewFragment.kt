package com.kelly.footballmatch.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.kelly.footballmatch.ApiRepository
import com.kelly.footballmatch.R
import com.kelly.footballmatch.model.repository.LocalRepositoryApi

import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.nestedScrollView

class TeamOverViewFragment: Fragment(), AnkoComponent<Context> {
    private lateinit var overViewString     : String
    private lateinit var overViewText       : TextView


    companion object {
        private const val TYPE_TEAMS = "TYPE_TEAMS"

        fun newInstance(overViewText:String): TeamOverViewFragment {
            val fragment = TeamOverViewFragment()
            fragment.arguments = bundleOf(TYPE_TEAMS to overViewText)

            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        overViewString = arguments?.get(TYPE_TEAMS) as String
        overViewText.text = overViewString
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        //super.onCreate(savedInstanceState)
        nestedScrollView {

            lparams(width = matchParent , height = matchParent)
            backgroundColor = Color.BLACK

            overViewText = textView {
                id = R.id.overView
                setTextAppearance(ctx, R.style.TextAppearance_AppCompat_Subhead)
                setTextColor(Color.WHITE)
            }.lparams {
                padding = dip(16)
                height = wrapContent
                width = matchParent

            }
        }
    }
}