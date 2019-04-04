package com.kelly.footballmatch.external.adapter.pageradapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.kelly.footballmatch.R
import com.squareup.picasso.Picasso

class ImageProfileAdapter : PagerAdapter{

    var context         :Context
    var imageProfiles   :ArrayList<String>

    lateinit var inflater: LayoutInflater

    constructor( context:Context, imageProfiles: ArrayList<String>): super(){
        this.context = context
        this.imageProfiles = imageProfiles

    }
    override fun getCount(): Int {
        return imageProfiles.size
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var oneImage:ImageView
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var imageview:View = inflater.inflate(R.layout.profile_slider_item, container, false)
        oneImage = imageview.findViewById(R.id.oneProfileImage)

        if(!imageProfiles[position].equals(null) && !imageProfiles[position].equals("")) {
            Picasso.get().load(imageProfiles[position]).into(oneImage)
        }
        container!!.addView(imageview)
        return imageview

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as LinearLayout)
    }
}
