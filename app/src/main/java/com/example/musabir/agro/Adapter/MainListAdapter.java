package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Musabir on 11/24/2017.
 */

public class MainListAdapter extends BaseAdapter {

    HashMap<String,ArrayList<ToxumMapper>> toxumMappers;
    Context context;
    private static RecyclerView horizontalList;
    private HashMap<String,ArrayList<ToxumMapper>> cityMap = new HashMap<>();
    ArrayList<String> cities;

    LinearLayout contactsView;
    int resource;
    private BigHorizontalRVAdapter bigHorizontalRVAdapter;
    private RecyclerView recycleView1;
    public  MainListAdapter(HashMap<String,ArrayList<ToxumMapper>> toxumMappers,int resource, Context context, ArrayList<String> cities){
        this.toxumMappers = toxumMappers;
        this.resource = resource;
        this.context = context;
        this.cities = cities;

        Log.d("---.>>>>>>>>>",cities.size()+ "szie");
    }

    @Override
    public int getCount() {
        return toxumMappers.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            contactsView = new LinearLayout(context);
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) context.getSystemService(inflater);
            vi.inflate(resource, contactsView, true);

        } else {
            contactsView = (LinearLayout) convertView;
        }
        final TextView city_name = (TextView) contactsView.findViewById(R.id.city_name);

        recycleView1 = (RecyclerView) contactsView.findViewById(R.id.horizontal_list);
        recycleView1.setNestedScrollingEnabled(false);
        horizontalList = recycleView1;
        horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        bigHorizontalRVAdapter = new BigHorizontalRVAdapter(context);
        horizontalList.setAdapter(bigHorizontalRVAdapter);

        city_name.setText(cities.get(position));
        bigHorizontalRVAdapter.setData(toxumMappers.get(cities.get(position)));

        return contactsView;
    }
}
