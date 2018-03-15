package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musabir.agro.Mapper.SuniMayalanmaMapper;
import com.example.musabir.agro.Mapper.XidmetGosterenSexslerMapper;
import com.example.musabir.agro.R;

import java.util.ArrayList;

/**
 * Created by Musabir on 11/24/2017.
 */

public class XidmetGosterenlerAdapter extends ArrayAdapter<XidmetGosterenSexslerMapper> {

    ArrayList<XidmetGosterenSexslerMapper> xidmetGosterenSexslerMappers;
    int resource;
    Context context;
    LinearLayout contactsView;



    public XidmetGosterenlerAdapter(@NonNull Context context, int resource, ArrayList<XidmetGosterenSexslerMapper> xidmetGosterenSexslerMappers) {
        super(context, resource, xidmetGosterenSexslerMappers);
        this.context = context;
        this.resource =resource;
        this.xidmetGosterenSexslerMappers = xidmetGosterenSexslerMappers;

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
        TextView service_name = (TextView) contactsView.findViewById(R.id.service_name);
        textView.setText(xidmetGosterenSexslerMappers.get(position).getName());
        addresss.setText(xidmetGosterenSexslerMappers.get(position).getRayon());
        service_name.setText(xidmetGosterenSexslerMappers.get(position).getXidmetAdi());


        return contactsView;
    }
}