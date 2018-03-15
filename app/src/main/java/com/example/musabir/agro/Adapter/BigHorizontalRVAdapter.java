package com.example.musabir.agro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musabir.agro.Activities.ToxumDetails;
import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Musabir on 11/6/2017.
 */

public class BigHorizontalRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ToxumMapper> mDataList;
    private int mRowIndex = -1;
    Context context;

    public BigHorizontalRVAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<ToxumMapper> data) {
        if (mDataList != data) {
            mDataList = data;
            notifyDataSetChanged();
        }
    }

    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView toxum_adi,category_adi;
        private RelativeLayout main1;
        ImageView img;


        public ItemViewHolder(View itemView) {
            super(itemView);
            toxum_adi = (TextView) itemView.findViewById(R.id.toxum_adi);
            category_adi = (TextView) itemView.findViewById(R.id.category_adi);
            main1 = (RelativeLayout) itemView.findViewById(R.id.main1);
            img = (ImageView) itemView.findViewById(R.id.toxum);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.custom_toxum_layout, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        holder.category_adi.setText(mDataList.get(position).getToxumSort());
        holder.toxum_adi.setText(mDataList.get(position).getToxumName());
        if(mDataList.get(position).getToxumName().contains("Buğda"))
            Picasso.with(context).load(R.drawable.bugda).into( holder.img);
        else if(mDataList.get(position).getToxumName().contains("Arpa"))
            Picasso.with(context).load(R.drawable.arpa).into( holder.img);
        else {
            Picasso.with(context).load(R.drawable.grass).into( holder.img);

        }
        //holder.itemView.setTag(position);
        holder.main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ToxumDetails.class);

                intent.putExtra("toxum",mDataList.get(position));

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}