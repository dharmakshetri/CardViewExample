package co.meettheteam;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
/**
 * Created by horror on 11/16/16.
 */

@RunWith(AndroidJUnit4.class)
public class MeetTheUserTest  {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void clickList_perfomUITest() throws  Exception{
        /*onView(withId(R.id.card_view))
                .perform(click())
                .check(matches(isDisplayed()));*/
        //onView(allOf(withId(R.id.card_view), withEffectiveVisibility(VISIBLE))).perform(click());

        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));


    }
}
