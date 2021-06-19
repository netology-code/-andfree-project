package ru.netology.netologyvoiceassistant

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstLessonInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testOutputTextViewIsDisplayed() {
        onView(
            withId(`is`(R.id.output))
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun testOutputTextViewHasVariableContent() {
        onView(
            withId(`is`(R.id.output))
        ).check(
            matches(
                withText("name: Ivan surname: Ivanov age: 37 height: 172.2")
            )
        )
    }

}