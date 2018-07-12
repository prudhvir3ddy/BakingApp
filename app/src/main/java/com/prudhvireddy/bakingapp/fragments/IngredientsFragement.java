package com.prudhvireddy.bakingapp.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prudhvireddy.bakingapp.R;
import com.prudhvireddy.bakingapp.adapters.IngredientsAdapter;
import com.prudhvireddy.bakingapp.models.IngredientsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientsFragement extends Fragment {
    @BindView(R.id.ingredients_recyclerview)
    RecyclerView recyclerView;
    private IngredientsAdapter adapter;
    private List<IngredientsModel> list;
    private static final String LIST_CONSTANT = "list";
    private LinearLayoutManager linearLayoutManager;

    public IngredientsFragement() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredients_fragment, container, false);
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList(LIST_CONSTANT);
            adapter = new IngredientsAdapter(list, getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new IngredientsAdapter(list, getContext());
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    public void setList(ArrayList<IngredientsModel> models) {
        list = models;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_CONSTANT, (ArrayList<? extends Parcelable>) list);
    }

}
