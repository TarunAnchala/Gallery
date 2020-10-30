package com.tarun.gallery;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GalleryEspressoTest {

    @Rule
    public final ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void smootScrollToEnd() {
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView)).perform(navigateToEnd());
        onView(ViewMatchers.isRoot()).perform(setDelay(3000));
    }

    @Test
    public void checkVisibility() {
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView)).check(matches(isDisplayed()));

    }

    @Test
    public void scrollToPosition() {
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView)).perform(navigate(16));
        onView(ViewMatchers.isRoot()).perform(setDelay(3000));
    }

    @Test
    public void scrollToPositionAndClick() {
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView)).perform(navigate(10));
        onView(withId(R.id.imagesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(ViewMatchers.isRoot()).perform(setDelay(3000));
    }


    @Test
    public void launchFullImageAndClickBack() {
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView)).perform(navigate(20));
        onView(ViewMatchers.isRoot()).perform(setDelay(2000));
        onView(withId(R.id.imagesRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
        onView(ViewMatchers.isRoot()).perform(setDelay(4000));
        Espresso.pressBack();
        onView(ViewMatchers.isRoot()).perform(setDelay(3000));
    }


    public ViewAction setDelay(int delay) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "setDelay";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }

    private ViewAction navigate(int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "navigate";
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
                Assert.assertNotNull(adapter);
                if (position < adapter.getItemCount()) {
                    recyclerView.smoothScrollToPosition(position);
                    uiController.loopMainThreadForAtLeast(3000);
                }
            }
        };

    }

    private ViewAction navigateToEnd() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "navigate To End";
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
                Assert.assertNotNull(adapter);
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    i = i + 3;
                    if (i < adapter.getItemCount() - 1) {
                        recyclerView.smoothScrollToPosition(i);
                        uiController.loopMainThreadForAtLeast(2000);
                    }
                }
            }
        };

    }
}
