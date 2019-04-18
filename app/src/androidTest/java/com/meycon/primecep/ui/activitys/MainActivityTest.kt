package com.meycon.primecep.ui.activitys


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.meycon.primecep.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
* * Classe de Teste Espresso (UI) - Entrada de dados - 29026-450;
*/

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun mainActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.et_cep),
                childAtPosition(
                    allOf(
                        withId(R.id.linear_form),
                        childAtPosition(
                            withId(R.id.coordinatorMain),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("29026"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.et_cep), withText("29026-"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear_form),
                        childAtPosition(
                            withId(R.id.coordinatorMain),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("29026-450"))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.et_cep), withText("29026-450"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear_form),
                        childAtPosition(
                            withId(R.id.coordinatorMain),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_cep), withText("Consultar"),
                childAtPosition(
                    allOf(
                        withId(R.id.linear_form),
                        childAtPosition(
                            withId(R.id.coordinatorMain),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
