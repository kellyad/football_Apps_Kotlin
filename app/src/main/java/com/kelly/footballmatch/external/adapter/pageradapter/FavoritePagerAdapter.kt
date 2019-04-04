package com.kelly.footballmatch.external.adapter.pageradapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kelly.footballmatch.external.constant.Types
import com.kelly.footballmatch.presentation.eventpage.homepage.view.EventFragment
import com.kelly.footballmatch.presentation.teampage.homepage.view.FootBallTeamFragment


class FavoritePagerAdapter(fragmentManager: FragmentManager, private val favorites: ArrayList<Types>) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        lateinit var fragment: Fragment
        when{
            position == 0 -> { fragment =  EventFragment.newInstance(favorites[position]) }
            position == 1 -> { fragment =  FootBallTeamFragment.newInstance(favorites[position]) }
        }
        return fragment
    }

    // Return the number of objects in the array.  
    override fun getCount(): Int {
        return favorites.size
    }

    override fun getPageTitle(position: Int): CharSequence?{
        var title = ""
        when {
            position == 0 -> title = "Matches"
            position == 1 -> title =  "Teams"
        }
        return title
    }
}