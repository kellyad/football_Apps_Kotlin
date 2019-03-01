package com.kelly.footballmatch.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kelly.footballmatch.constant.Types
import com.kelly.footballmatch.view.fragment.EventFragment

class EventPagerAdapter(fragmentManager: FragmentManager, private val matchs: ArrayList<Types>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // Return the Fragment associated with the object located at the specified position 
    override fun getItem(position: Int): Fragment {
        return EventFragment.newInstance(matchs[position])
    }

    // Return the number of objects in the array.  
    override fun getCount(): Int {
        return matchs.size
    }

    override fun getPageTitle(position: Int): CharSequence?{
      return matchs[position].toString()
    }
}
