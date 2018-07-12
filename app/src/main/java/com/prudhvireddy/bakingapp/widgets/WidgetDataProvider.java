package com.prudhvireddy.bakingapp.widgets;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.prudhvireddy.bakingapp.R;
import com.prudhvireddy.bakingapp.models.MainModel;
import com.prudhvireddy.bakingapp.utils.Urls;

import java.util.ArrayList;

import static com.prudhvireddy.bakingapp.activities.MainActivity.list;

class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private final Context context;

    public WidgetDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        fetchData();
    }

    private void fetchData() {
        AndroidNetworking.get(Urls.url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(MainModel.class, new ParsedRequestListener<ArrayList<MainModel>>() {
                    @Override
                    public void onResponse(ArrayList<MainModel> response) {
                        list = response;
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widgetmodel);
        remoteViews.setTextViewText(R.id.recipe_name, list.get(position).getHome_name());
        remoteViews.removeAllViews(R.id.ingerdient_list);
        for (int i = 0; i < list.get(position).getIngredients().size(); i++) {
            RemoteViews ingredient = new RemoteViews(context.getPackageName(), R.layout.widget_ingredient_list);
            ingredient.setTextViewText(R.id.ingredient, list.get(position).getIngredients().get(i).getIngredient());
            ingredient.setTextViewText(R.id.measure, list.get(position).getIngredients().get(i).getMeasure());
            ingredient.setTextViewText(R.id.quantity, list.get(position).getIngredients().get(i).getQuantity() + "");

            remoteViews.addView(R.id.ingerdient_list, ingredient);
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
