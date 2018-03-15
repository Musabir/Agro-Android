package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musabir.agro.Mapper.SuniMayalanmaMapper;
import com.example.musabir.agro.R;

import java.util.ArrayList;

/**
 * Created by Musabir on 11/24/2017.
 */

public class CustomMayalanmaAdapter extends ArrayAdapter<SuniMayalanmaMapper> {


    ArrayList<SuniMayalanmaMapper> suniMayalanmaMappers;
    int resource;
    Context context;
    LinearLayout contactsView;



    public CustomMayalanmaAdapter(@NonNull Context context, int resource, ArrayList<SuniMayalanmaMapper> suniMayalanmaMappers) {
        super(context, resource, suniMayalanmaMappers);
        this.context = context;
        this.resource =resource;
        this.suniMayalanmaMappers = suniMayalanmaMappers;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);

        } else {
            contactsView = (LinearLayout) convertView;
        }


        TextView textView = (TextView) contactsView.findViewById(R.id.txt);
        TextView addresss = (TextView) contactsView.findViewById(R.id.addresss);
        TextView nomre = (TextView) contactsView.findViewById(R.id.nomre);
        TextView service_name = (TextView) contactsView.findViewById(R.id.service_name);
        textView.setText(suniMayalanmaMappers.get(position).getName());
        addresss.setText(suniMayalanmaMappers.get(position).getRayon());
        service_name.setText(suniMayalanmaMappers.get(position).getXidmetAdi());
        nomre.setText(suniMayalanmaMappers.get(position).getContact_number());

        return contactsView;
    }
}
