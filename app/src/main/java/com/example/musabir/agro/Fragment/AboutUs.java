package com.example.musabir.agro.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musabir.agro.R;

/**
 * Created by Musabir on 11/25/2017.
 */

public class AboutUs extends PreferenceFragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_us, container, false);
        TextView textView = (TextView) view.findViewById(R.id.aboutus_txt);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","mmmusabir@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Agro");
                startActivity(Intent.createChooser(emailIntent, "Feedback"));
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle("Haqqımızda");

    }
}
