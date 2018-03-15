package com.example.musabir.agro.Mapper;

/**
 * Created by Musabir on 11/24/2017.
 */

public class SuniMayalanmaMapper {

    String name;
    String xidmetAdi;
    String rayon;
    String contact_number;

    public SuniMayalanmaMapper(String name, String xidmetAdi, String rayon, String contact_number) {
        this.name = name;
        this.xidmetAdi = xidmetAdi;
        this.rayon = rayon;
        this.contact_number = contact_number;
    }
    public SuniMayalanmaMapper(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXidmetAdi() {
        return xidmetAdi;
    }

    public void setXidmetAdi(String xidmetAdi) {
        this.xidmetAdi = xidmetAdi;
    }



    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

}
