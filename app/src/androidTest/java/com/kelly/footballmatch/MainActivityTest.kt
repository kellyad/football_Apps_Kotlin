package com.kelly.footballmatch


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kelly.footballmatch.R.id.*
import com.kelly.footballmatch.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun onCreate() {

    }

//    @Test
//    fun showLoading() {
//    }
//
//    @Test
//    fun hideLoading() {
//    }
//
//    @Test
//    fun showLastMatch() {
//    }
//
//    @Test
//    fun showNextMatch() {
//    }
//
//    @Test
//    fun showFavoriteMatch() {
//    }
//
//    @Test
//    fun visible() {
//    }
//
//    @Test
//    fun invisible() {
//    }

    @Test
    fun mainActivityTest() {
        //matches upcoming
        delay()

        onView(Matchers.allOf(withId(spinner), isDisplayed()))
                .perform(click())
        onView(ViewMatchers.withText("Spanish La Liga")).perform(click())

        delay()
        onView(Matchers.allOf(withId(viewpager), isDisplayed()))
                .perform(click())
        delay()

        onView(withId(awayImg)).check(matches(isDisplayed()))
        onView(withId(homeImg)).check(matches(isDisplayed()))

        onView(withId(favorite)).perform(click())

        pressBack()
        delay()

        //matches last
        onView(ViewMatchers.withText("LAST_MATCH")).perform(click())
        delay()

        onView(withId(R.id.viewpager)).perform(swipeLeft())
        delay()

        onView(Matchers.allOf(withId(spinner), isDisplayed()))
                .perform(click())
        onView(ViewMatchers.withText("Spanish La Liga")).perform(click())
        delay()

        onView(Matchers.allOf(withId(viewpager), isDisplayed()))
                .perform(click())
        delay()

        onView(withId(awayImg)).check(matches(isDisplayed()))
        onView(withId(homeImg)).check(matches(isDisplayed()))

        onView(withId(favorite)).perform(click())

        pressBack()
        delay()

        // teams
        onView(withId(teams)).perform(click())
        delay()

        onView(Matchers.allOf(withId(spinner), isDisplayed()))
                .perform(click())
        onView(ViewMatchers.withText("Spanish La Liga")).perform(click())
        delay()

        onView(withId(recyclerView))
                .check(matches(isDisplayed()))

        onView(withId(recyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()

        onView(withId(team_pic)).check(matches(isDisplayed()))
        onView(withId(overView)).check(matches(isDisplayed()))

        onView(ViewMatchers.withText(R.string.players)).perform(click())
        delay()

        onView(withId(recyclerView)).check(matches(isDisplayed()))
        delay()

        onView(withId(recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()

        onView(withId(player_pic)).check(matches(isDisplayed()))

        pressBack()
        delay()


        onView(withId(favorite)).perform(click())
        pressBack()
        delay()
        onView(withId(favorites)).perform(click())

        delay()

        onView(withId(R.id.viewpager)).perform(swipeLeft())


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