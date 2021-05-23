package com.example.taskmaster;

import android.content.Intent;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.cert.PKIXCertPathChecker;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }


    @Test
    public void testMainActivityElements() {

        onView(withText("My Tasks")).check(matches(isDisplayed()));

        onView(withId(R.id.button)).check(matches(withText("Add Task")));
        onView(withId(R.id.button2)).check(matches(withText("All Tasks")));
        onView(withId(R.id.TaskAbtn)).check(matches(withText("Task A ")));
        onView(withId(R.id.TaskBbtn)).check(matches(withText("TaskB")));
        onView(withId(R.id.taskCbtn)).check(matches(withText("taskC")));
        onView(withId(R.id.settingsbtn)).check(matches(withText("Settings ")));

        onView(withId(R.id.usernamehomepage)).check(matches(isDisplayed()));


        //recyclerView
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));



// onView(withId(R.id.settingsbtn))
//                .perform(typeText(textViewusername), closeSoftKeyboard());

//        onView(withId(R.id.taskCbtn))
//                .perform();


    }

//    @Test
//    public void testRecyclerView() {
//  onView(R.id.recyclerView).perform(click());
//
//
//    }


    @Test
    public void testSettingsBtn() {
        onView(withId(R.id.settingsbtn)).perform(click());
        onView(withId(R.id.editTextTextuserName)).perform(replaceText("user"));
        onView(withId(R.id.usernameSavebtn)).perform(click());
        onView(withId(R.id.usernamehomepage)).check(matches(withText("user's page")));
    }


    @Test
    public void testRecycleView(){
//        onView(withId(R.id.recyclerView)).perform(click());

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

//        onView(withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.scrollTo(ReactionOnItem(hasDescendant(withText("task2")), click())));

        onView(withId(R.id.detailpagetitle)).check(matches(withText("task1")));
//        onView(withId(R.id.detailpagestae)).check(matches(withText("state")));
//        onView(withId(R.id.detaildescription)).check(matches(withText("description ")));
//
//        onView(withId(R.id.deletebtn)).check(matches(withText("Delete Task")));


    }

}












