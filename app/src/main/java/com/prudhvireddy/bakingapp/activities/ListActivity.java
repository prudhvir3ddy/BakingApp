package com.prudhvireddy.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prudhvireddy.bakingapp.R;
import com.prudhvireddy.bakingapp.adapters.StepsAdapter;
import com.prudhvireddy.bakingapp.fragments.IngredientsFragement;
import com.prudhvireddy.bakingapp.fragments.VideosFragment;
import com.prudhvireddy.bakingapp.models.IngredientsModel;
import com.prudhvireddy.bakingapp.models.MainModel;
import com.prudhvireddy.bakingapp.models.StepsModel;
import com.prudhvireddy.bakingapp.utils.OnItemTouchListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.ingredientsviewtext)
    TextView ingredientsnumber;
    @BindView(R.id.stepsrecyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.ingredientcardview)
    CardView cardView;
    private StepsAdapter adapter;
    private ArrayList<StepsModel> stepList;
    private ArrayList<IngredientsModel> ingredientsList;
    private MainModel recipeModel;
    private boolean twoPane;
    private String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recipeModel = bundle.getParcelable("data");
            recipeName = recipeModel.getHome_name();
            getSupportActionBar().setTitle(recipeName);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stepList = recipeModel.getSteps();
        ingredientsList = recipeModel.getIngredients();
        adapter = new StepsAdapter(stepList, this);
        recyclerView.setAdapter(adapter);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        twoPane = findViewById(R.id.fulldetailsframelayout) != null;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twoPane) {
                    IngredientsFragement ingredientsFragement = new IngredientsFragement();
                    ingredientsFragement.setList(ingredientsList);
                    fragmentManager.beginTransaction()
                            .replace(R.id.fulldetailsframelayout, ingredientsFragement)
                            .commit();
                } else {
                    Bundle ingredients_bundel = new Bundle();
                    ingredients_bundel.putParcelableArrayList("ingredient_bundle", ingredientsList);
                    Intent i1 = new Intent(getApplicationContext(), DetailsActivity.class);
                    i1.putExtras(ingredients_bundel);
                    startActivity(i1);
                }
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemTouchListener(this, new OnItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (twoPane) {
                    VideosFragment videosFragment = new VideosFragment();
                    videosFragment.setPosition(position);
                    videosFragment.setStepList(stepList);
                    fragmentManager.beginTransaction()
                            .replace(R.id.fulldetailsframelayout, videosFragment)
                            .commit();

                } else {
                    Bundle steps_bundle = new Bundle();
                    steps_bundle.putParcelableArrayList("steps_bundle", stepList);
                    steps_bundle.putInt("position", position);
                    Intent i2 = new Intent(getApplicationContext(), DetailsActivity.class);
                    i2.putExtras(steps_bundle);
                    startActivity(i2);
                }
            }
        }));
    }
}
