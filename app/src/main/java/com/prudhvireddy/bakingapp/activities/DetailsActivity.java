package com.prudhvireddy.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.prudhvireddy.bakingapp.R;
import com.prudhvireddy.bakingapp.fragments.IngredientsFragement;
import com.prudhvireddy.bakingapp.fragments.VideosFragment;
import com.prudhvireddy.bakingapp.models.IngredientsModel;
import com.prudhvireddy.bakingapp.models.StepsModel;

import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity {

    private ArrayList<IngredientsModel> ingredientsList;
    private ArrayList<StepsModel> stepsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ingredientsList = getIntent().getExtras().getParcelableArrayList("ingredient_bundle");
        stepsList = getIntent().getExtras().getParcelableArrayList("steps_bundle");

        if (stepsList != null && savedInstanceState == null) {

            VideosFragment videosFragment = new VideosFragment();
            videosFragment.setPosition(getIntent().getIntExtra("position", 0));
            videosFragment.setStepList(stepsList);
            fragmentManager.beginTransaction()
                    .replace(R.id.details_frame, videosFragment)
                    .commit();
        } else if (savedInstanceState == null) {

            IngredientsFragement ingredientsFragement = new IngredientsFragement();
            ingredientsFragement.setList(ingredientsList);
            fragmentManager.beginTransaction()
                    .replace(R.id.details_frame, ingredientsFragement)
                    .commit();
        } else {

            fragmentManager.findFragmentById(R.id.details_frame);
        }

    }
}
