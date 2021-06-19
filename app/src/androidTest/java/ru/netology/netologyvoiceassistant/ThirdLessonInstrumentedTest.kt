package ru.netology.netologyvoiceassistant

import android.view.View
import android.widget.ListView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
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
class ThirdLessonInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testClearMenuItemCleanList() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("How big is the universe?"),
            pressImeActionButton()
        )

        Thread.sleep(5000L)

        onView(
            withId(R.id.action_clear)
        ).perform(
            click()
        )

        onView(
            withId(R.id.pods_list)
        ).check(
            matches(
                hasItems(0)
            )
        )
    }

    @Test
    fun testClearMenuItemCleanRequestField() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("How big is the universe?"),
            pressImeActionButton()
        )

        Thread.sleep(5000L)

        onView(
            withId(R.id.action_clear)
        ).perform(
            click()
        )

        onView(
            withId(R.id.text_input_edit)
        ).check(
            matches(
                withText("")
            )
        )
    }

    @Test
    fun testRequest() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("How big is the universe?"),
            pressImeActionButton()
        )

        Thread.sleep(5000L)

        onView(
            withId(R.id.pods_list)
        ).check(
            matches(
                hasItems(3)
            )
        )

        onData(
            anything()
        ).inAdapterView(
            withId(R.id.pods_list)
        ).atPosition(
            0
        ).onChildView(
            withId(R.id.title)
        ).check(
            matches(
                withText("Interpretation")
            )
        )
    }

    @Test
    fun testProgressBar() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("Hello World"),
            pressImeActionButton()
        )

        onView(
            withId(R.id.progress_bar)
        ).check(
            matches(
                isDisplayed()
            )
        )

        // Better to use IdlingResource
        Thread.sleep(5000L)

        onView(
            withId(R.id.progress_bar)
        ).check(
            matches(
                not(isDisplayed())
            )
        )
    }

    @Test
    fun testSnackBarIsDisplayed() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi disable")
            executeShellCommand("svc data disable")
        }

        Thread.sleep(1000L)

        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("Hello World"),
            pressImeActionButton()
        )

        Thread.sleep(1000L)

        onView(
            withId(com.google.android.material.R.id.snackbar_text)
        ).check(
            matches(
                isDisplayed()
            )
        )

        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi enable")
            executeShellCommand("svc data enable")
        }

        Thread.sleep(1000L)
    }

    @Test
    fun testSnackBarHasAction() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi disable")
            executeShellCommand("svc data disable")
        }

        Thread.sleep(1000L)

        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("Hello World"),
            pressImeActionButton()
        )

        Thread.sleep(1000L)

        onView(
            withId(com.google.android.material.R.id.snackbar_action)
        ).check(
            matches(
                allOf(
                    isDisplayed(),
                    withText(android.R.string.ok)
                )
            )
        )

        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi enable")
            executeShellCommand("svc data enable")
        }

        Thread.sleep(1000L)
    }

    @Test
    fun testSnackAction() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText(""),
            pressImeActionButton()
        )

        Thread.sleep(5000L)

        onView(
            withId(com.google.android.material.R.id.snackbar_action)
        ).perform(
            click()
        ).check(
            doesNotExist()
        )
    }

    @Test
    fun testEditTextError() {
        onView(
            withId(R.id.text_input_edit)
        ).perform(
            replaceText("п!!,,**$$№@@@"),
            pressImeActionButton()
        )

        // Better to use IdlingResource
        Thread.sleep(5000L)

        onView(
            withId(R.id.text_input_edit)
        ).check(
            matches(
                hasErrorText(
                    InstrumentationRegistry.getInstrumentation().targetContext
                        .getText(R.string.error_do_not_understand).toString()
                )
            )
        )
    }
}

fun hasItems(expectedCount: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("ListView has $expectedCount items")
    }

    override fun matchesSafely(view: View?): Boolean {
        val actualCount = (view as? ListView)?.adapter?.count
        if (actualCount == null) {
            return false
        }

        return expectedCount == actualCount
    }
}