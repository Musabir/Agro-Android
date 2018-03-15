package com.example.musabir.agro.Mapper;

import java.io.Serializable;

/**
 * Created by Musabir on 11/24/2017.
 */

public class XidmetGosterenSexslerMapper implements Serializable {

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

    public XidmetGosterenSexslerMapper(){}
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

    public String getQiymet() {
        return qiymet;
    }

    public void setQiymet(String qiymet) {
        this.qiymet = qiymet;
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

    public String getQeyd() {
        return qeyd;
    }

    public void setQeyd(String qeyd) {
        this.qeyd = qeyd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
