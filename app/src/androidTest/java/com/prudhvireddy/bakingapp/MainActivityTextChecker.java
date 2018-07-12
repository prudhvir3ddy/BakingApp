package com.prudhvireddy.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.prudhvireddy.bakingapp.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTextChecker {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void CheckRecipeNameOnGridView(){
        try {
            sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        onView(allOf(withId(R.id.recipe_name),withText("Nutella Pie")));
        onView(allOf(withId(R.id.recipe_name),withText("Brownies")));
        onView(allOf(withId(R.id.recipe_name),withText("Yellow Cake")));
        onView(allOf(withId(R.id.recipe_name),withText("CheeseCake")));

    }
}
