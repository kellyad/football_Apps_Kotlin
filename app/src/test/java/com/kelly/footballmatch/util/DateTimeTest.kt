package com.kelly.footballmatch.util

import com.kelly.footballmatch.external.util.DateTime
import org.junit.Test

import org.junit.Assert.*

class DateTimeTest {

    @Test
    fun getShortDate() {
        assertEquals("25 October 2018", DateTime.getShortDate("2018-10-25"))
    }

    @Test
    fun getLongDate() {
        assertEquals("Mon, 12 Nov 2018", DateTime.getLongDate("2018-11-12"))
    }


}