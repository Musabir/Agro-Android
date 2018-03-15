package com.example.musabir.agro.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.musabir.agro.Activities.XidmetGosterenDetails;
import com.example.musabir.agro.Adapter.XidmetGosterenlerAdapter;
import com.example.musabir.agro.Mapper.XidmetGosterenSexslerMapper;
import com.example.musabir.agro.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Musabir on 11/24/2017.
 */

public class XidmetGosterenSexsler extends PreferenceFragment {

    View view;
    ListView listView;
    XidmetGosterenlerAdapter listAdapter;
    ArrayList<XidmetGosterenSexslerMapper> xidmetGosterenSexslerMappers;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.xidmet_gosteren_listview, container, false);
         xidmetGosterenSexslerMappers = new ArrayList<>();

        listView = (ListView) view.findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setTitle("Xidmət təklif edənlər");
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                swipeRefreshLayout.setRefreshing(true);
                                                xidmetGosterenSexslerMappers = new ArrayList<>();

                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("XidmetElaveEdenler");
                                                ref.addListenerForSingleValueEvent(
                                                        new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                getXidmetGosterenSexsler((Map<String,Object>) dataSnapshot.getValue());
                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {
                                                            }
                                                        });
                                            }
                                        }
                );
            }
        });
        //    public XidmetGosterenSexslerMapper(String name, String xidmetAdi, String qiymet, String rayon, String contact_number, String qeyd) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("XidmetElaveEdenler");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        getXidmetGosterenSexsler((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), XidmetGosterenDetails.class);
                intent.putExtra("xidmet",xidmetGosterenSexslerMappers.get(i));
                getActivity().startActivity(intent);

            }
        });

        return view;
    }
    private void getXidmetGosterenSexsler(Map<String,Object> users) {
        xidmetGosterenSexslerMappers.clear();
        ArrayList<Long> phoneNumbers = new ArrayList<>();
        Log.d("---???",users.size() +" size");
        for (Map.Entry<String, Object> entry : users.entrySet()){
            XidmetGosterenSexslerMapper mapper = new XidmetGosterenSexslerMapper();
            Map singleUser = (Map) entry.getValue();
            mapper.setName((String)singleUser.get("name"));
            mapper.setContact_number((String)singleUser.get("contact_number"));
            mapper.setEmail((String)singleUser.get("email"));
            mapper.setQeyd((String)singleUser.get("qeyd"));
            mapper.setQiymet((String)singleUser.get("qiymet"));
            mapper.setXidmetAdi((String)singleUser.get("xidmetAdi"));
            mapper.setRayon((String)singleUser.get("rayon"));
            xidmetGosterenSexslerMappers.add(mapper);
        }

        listAdapter = new XidmetGosterenlerAdapter(getActivity(), R.layout.xidmet_gosteren_sexsler, xidmetGosterenSexslerMappers);
        listView.setAdapter(listAdapter);
        swipeRefreshLayout.setRefreshing(false);

    }
    
}
