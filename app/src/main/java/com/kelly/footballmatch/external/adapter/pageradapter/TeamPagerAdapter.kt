package com.kelly.footballmatch.external.adapter.pageradapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kelly.footballmatch.presentation.teampage.homepage.view.TeamOverViewFragment
import com.kelly.footballmatch.presentation.playerpage.homepage.view.TeamPlayersFragment

class TeamPagerAdapter(fragmentManager: FragmentManager, private val teams: ArrayList<String>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // Return the Fragment associated with the object located at the specified position 
    override fun getItem(position: Int): Fragment {
          lateinit var fragment: Fragment
        when{
            position == 0 -> { fragment =  TeamOverViewFragment.newInstance(teams[position]) }
            position == 1 -> { fragment =  TeamPlayersFragment.newInstance(teams[position]) }
        }
        return fragment
    }

    // Return the number of objects in the array.  
    override fun getCount(): Int {
        return teams.size
    }

    override fun getPageTitle(position: Int): CharSequence?{
        var title = ""
        when {
            position == 0 -> title = "Overview"
            position == 1 -> title =  "Players"
        }
        return title
    }
}