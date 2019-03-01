package com.dicoding.footballclub


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dicoding.footballclub.R.id.*
import com.dicoding.footballclub.EventActivity
import kotlinx.android.synthetic.main.detail_activity.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class EventActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(EventActivity::class.java)
    @Test
    fun onCreate() {

    }

    @Test
    fun showLoading() {
    }

    @Test
    fun hideLoading() {
    }

    @Test
    fun showLastMatch() {
    }

    @Test
    fun showNextMatch() {
    }

    @Test
    fun showFavoriteMatch() {
    }

    @Test
    fun visible() {
    }

    @Test
    fun invisible() {
    }

    @Test
    fun mainActivityTest() {
        delay()
        onView(withId(recycler))
                .check(matches(isDisplayed()))
        onView(withId(recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()

        onView(withId(awayImg)).check(matches(isDisplayed()))
        onView(withId(homeImg)).check(matches(isDisplayed()))

        pressBack()
        delay()

        onView(withId(upcoming_match)).perform(click())
        delay()
        onView(withId(recycler))
                .check(matches(isDisplayed()))

        onView(withId(recycler))
                .check(matches(isDisplayed()))
        onView(withId(recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()

        onView(withId(awayImg)).check(matches(isDisplayed()))
        onView(withId(homeImg)).check(matches(isDisplayed()))

        onView(withId(favorite)).perform(click())

        pressBack()
        delay()

        onView(withId(favorites)).perform(click())

        delay()

        onView(withId(recycler))
                .check(matches(isDisplayed()))

        onView(withId(recycler)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay()

        onView(withId(awayImg)).check(matches(isDisplayed()))
        onView(withId(homeImg)).check(matches(isDisplayed()))

        onView(withId(favorite)).perform(click())

        pressBack()

        delay()

        onView(withId(swipeRefresh)).perform(swipeDown())

        delay()

    }

    private fun delay(){
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}