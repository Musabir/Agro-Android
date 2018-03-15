package com.example.musabir.agro.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musabir.agro.Fragment.MainScreenFragment;
import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.R;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ToxumDetails extends AppCompatActivity implements ObservableScrollViewCallbacks {
    Toolbar toolbar;
    ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    TextView ad_soyad,toxumun_adi,toxumun_sortu,region,nomre,email,eml,qiymet,qiymettxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_toxum_details);

        ToxumMapper toxumMapper = (ToxumMapper)getIntent().getSerializableExtra("toxum");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ((Toolbar) toolbar).setTitle(toxumMapper.getToxumName());
        setSupportActionBar((Toolbar) toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ad_soyad = (TextView) findViewById(R.id.ad_soyad);
        toxumun_adi = (TextView) findViewById(R.id.toxum_adi);
        toxumun_sortu = (TextView) findViewById(R.id.sort_name);
        region = (TextView) findViewById(R.id.region);
        qiymet = (TextView) findViewById(R.id.qiymet);
        nomre = (TextView) findViewById(R.id.nomre);
        email = (TextView) findViewById(R.id.email);
        eml = (TextView) findViewById(R.id.eml);
        ImageView im = (ImageView) findViewById(R.id.anchor);
        if(toxumMapper.getToxumName().contains("Buğda"))
        Picasso.with(this).load(R.drawable.bugda).into(im);
        else if(toxumMapper.getToxumName().contains("Arpa"))
            Picasso.with(this).load(R.drawable.arpa).into(im);
        else {
            Picasso.with(this).load(R.drawable.grass).into(im);

        }
        qiymettxt = (TextView) findViewById(R.id.qiymettxt);
        ad_soyad.setText(toxumMapper.getSellerName());
        toxumun_adi.setText(toxumMapper.getToxumName());
        toxumun_sortu.setText(toxumMapper.getToxumSort());
        region.setText(toxumMapper.getRegion());
        nomre.setText(toxumMapper.getContact());
        email.setText(toxumMapper.getEmail());
        qiymet.setText(toxumMapper.getQiymet());
        if(email.getText().toString().equals("")||email.getText().toString().equals("null")){
            email.setVisibility(View.GONE);
            eml.setVisibility(View.GONE);
        }
        if(qiymet.getText().toString().equals("")||qiymet.getText().toString().equals("null")){
            qiymet.setVisibility(View.GONE);
            qiymettxt.setVisibility(View.GONE);
        }
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, ContextCompat.getColor(this, R.color.colorPrimaryDark)));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);


    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);


        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
