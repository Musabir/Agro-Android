package com.example.musabir.agro.Compare;

import android.support.annotation.NonNull;

/**
 * Created by Musabir on 11/25/2017.
 */

public class XidmetGosterenSexslerMapper implements Comparable {


    String name;
    String xidmetAdi;
    String qiymet;
    String rayon;
    String contact_number;
    String qeyd;
    String email;

    public XidmetGosterenSexslerMapper(String name, String xidmetAdi, String qiymet, String rayon, String contact_number, String qeyd,String email) {
        this.name = name;
        this.xidmetAdi = xidmetAdi;
        this.qiymet = qiymet;
        this.rayon = rayon;
        this.contact_number = contact_number;
        this.qeyd = qeyd;
        this.email = email;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
