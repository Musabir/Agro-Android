package com.example.musabir.agro.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.musabir.agro.Mapper.ToxumMapper;
import com.example.musabir.agro.Mapper.XidmetGosterenSexslerMapper;
import com.example.musabir.agro.R;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

public class XidmetGosterenDetails extends AppCompatActivity implements ObservableScrollViewCallbacks {
    Toolbar toolbar;
    ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    TextView ad_soyad,qeyd,xidmetin_adi,region,nomre,email,eml,qiymet,qiymettxt,qeydtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_xidmet_gosteren_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        XidmetGosterenSexslerMapper xidmetGosterenSexslerMapper = (XidmetGosterenSexslerMapper)getIntent().getSerializableExtra("xidmet");

        ((Toolbar) toolbar).setTitle(xidmetGosterenSexslerMapper.getXidmetAdi());
        setSupportActionBar((Toolbar) toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ad_soyad = (TextView) findViewById(R.id.ad_soyad);
        qeyd = (TextView) findViewById(R.id.sort_name);
        xidmetin_adi = (TextView) findViewById(R.id.toxum_adi);
        region = (TextView) findViewById(R.id.region);
        qiymet = (TextView) findViewById(R.id.qiymet);
        nomre = (TextView) findViewById(R.id.nomre);
        email = (TextView) findViewById(R.id.email);
        eml = (TextView) findViewById(R.id.eml);
        qeydtxt = (TextView) findViewById(R.id.qeyd);
        qiymettxt = (TextView) findViewById(R.id.qiymettxt);
        ad_soyad.setText(xidmetGosterenSexslerMapper.getName());
        qeyd.setText(xidmetGosterenSexslerMapper.getQeyd());
        xidmetin_adi.setText(xidmetGosterenSexslerMapper.getXidmetAdi());
        region.setText(xidmetGosterenSexslerMapper.getRayon());
        nomre.setText(xidmetGosterenSexslerMapper.getContact_number());
        email.setText(xidmetGosterenSexslerMapper.getEmail());
        qiymet.setText(xidmetGosterenSexslerMapper.getQiymet());
        if(email.getText().toString().equals("")||email.getText().toString().equals("null")){
            email.setVisibility(View.GONE);
            eml.setVisibility(View.GONE);
        }
        if(qiymet.getText().toString().equals("")||qiymet.getText().toString().equals("null")){
            qiymet.setVisibility(View.GONE);
            qiymettxt.setVisibility(View.GONE);
        }
        if(qeyd.getText().toString().equals("")||qeyd.getText().toString().equals("null")){
            qeyd.setVisibility(View.GONE);
            qeydtxt.setVisibility(View.GONE);
        }
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, ContextCompat.getColor(this, R.color.colorPrimary)));

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
