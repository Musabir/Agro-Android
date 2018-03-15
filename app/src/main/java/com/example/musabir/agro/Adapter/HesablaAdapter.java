package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.musabir.agro.Mapper.HesablaMapper;
import com.example.musabir.agro.Mapper.XidmetGosterenSexslerMapper;
import com.example.musabir.agro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Musabir on 11/25/2017.
 */
public class HesablaAdapter extends ArrayAdapter<HesablaMapper> {

    ArrayList<HesablaMapper> xidmetGosterenSexslerMappers;
    int resource;
    Context context;
    LinearLayout contactsView;
    int icons[];



    public HesablaAdapter(@NonNull Context context, int resource, ArrayList<HesablaMapper> xidmetGosterenSexslerMappers,int icons[]) {
        super(context, resource, xidmetGosterenSexslerMappers);
        this.context = context;
        this.resource =resource;
        this.xidmetGosterenSexslerMappers = xidmetGosterenSexslerMappers;
        this.icons = icons;

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

        TextView textView = (TextView) contactsView.findViewById(R.id.toxum_adi);
        TextView category_adi = (TextView) contactsView.findViewById(R.id.category_adi);
        TextView size = (TextView) contactsView.findViewById(R.id.size);
        size.setText(xidmetGosterenSexslerMappers.get(position).getSize());
        textView.setText(xidmetGosterenSexslerMappers.get(position).getXidmet());

        ImageView imageView = (ImageView) contactsView.findViewById(R.id.toxum);
        Glide.with(context).load(icons[position]).into(imageView);

        //Picasso.with(context).load(icons[position]).into(imageView);

        category_adi.setText(xidmetGosterenSexslerMappers.get(position).getQiymet());

        return contactsView;
    }
}