package ru.netology.netologyvoiceassistant

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class FourthLessonInstrumentedTest {

    @get:Rule
    var activityScenarioRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testSendRecognizerIntent() {
        onView(
            withId(R.id.voice_input_button)
        ).perform(
            click()
        )

        intended(
            allOf(
                hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                hasExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                ),
                hasExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.request_hint)
                ),
                hasExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            )
        )
    }

    @Test
    fun testReceiveActivityResult() {
        val resultData = Intent()
        val data = Bundle(1)
        data.putStringArrayList(
            RecognizerIntent.EXTRA_RESULTS,
            arrayListOf("How big is the universe?")
        )
        resultData.putExtras(data)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        intending(
            hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        ).respondWith(
            result
        )

        onView(
            withId(R.id.voice_input_button)
        ).perform(
            click()
        )

        onView(
            withId(R.id.text_input_edit)
        ).check(
            matches(
                withText("How big is the universe?")
            )
        )
    }

}