package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomSearchAdapter extends ArrayAdapter<ToxumMapper> {
    List<ToxumMapper> items;

    Context context;
    public CustomSearchAdapter(Context context, int resource, List<ToxumMapper> items) {
        super(context, resource, items);
        this.items = items;
        this.context = context;


    }

    @Nullable
    @Override
    public ToxumMapper getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_search_layout, null);
        }
        ToxumMapper toxumMapper =items.get(position);
        TextView toxum_name = (TextView) v.findViewById(R.id.toxum_name);
        TextView toxum_sort = (TextView) v.findViewById(R.id.toxum_sort);
        TextView toxum_region = (TextView) v.findViewById(R.id.toxum_region);
        ImageView imageView = (ImageView) v.findViewById(R.id.search_icon);

        toxum_name.setText(toxumMapper.getToxumName());
        toxum_sort.setText(toxumMapper.getToxumSort());
        toxum_region.setText(toxumMapper.getRegion());
        if(toxum_name.getText().toString().contains("Buğda"))
        Picasso.with(context).load(R.drawable.bugda).into(imageView);
        else if(toxum_name.getText().toString().contains("Arpa"))
            Picasso.with(context).load(R.drawable.arpa).into(imageView);
        else
            Picasso.with(context).load(R.drawable.grass).into(imageView);


        return v;
    }
}
