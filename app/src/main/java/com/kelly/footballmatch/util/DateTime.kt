package com.kelly.footballmatch.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTime {

    fun changeToGMTFormat(strDate: String?, strTime: String?): Date? {
        var Time = strTime
        if(strTime.equals(null)){
            Time = "00:00:00"
        }
        var dateTime = Date()
        try {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            format.timeZone = TimeZone.getTimeZone("UTC")
             dateTime = format.parse("$strDate $Time")

        }catch(e: Exception ){
            val format = SimpleDateFormat("yyyy-MM-dd")
            //format.timeZone = TimeZone.getTimeZone("UTC")
            dateTime = format.parse("$strDate")
            Log.d("err",e.toString())

        }
        return dateTime
    }

    private fun formatDate(date: String, format: String): String {
        var result = ""
        val old = SimpleDateFormat("yyyy-MM-dd")

        try {
            val oldDate = old.parse(date)
            val newFormat = SimpleDateFormat(format)
            result = newFormat.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun getShortDate(date: String?): String {
        return formatDate(date.toString(), "dd MMMM yyyy")
    }

    fun getLongDate(date: String?): String {
        return formatDate(date.toString(), "EEE, dd MMM yyyy")
    }
}
