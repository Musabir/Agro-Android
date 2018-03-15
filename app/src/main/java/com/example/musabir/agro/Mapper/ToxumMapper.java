package com.example.musabir.agro.Mapper;

import java.io.Serializable;

/**
 * Created by Musabir on 11/24/2017.
 */

public class ToxumMapper implements Serializable {

    private String toxumName;
    private String toxumSort;
    private String contact;
    private String region;
    private String sellerName;
    private String email;
    private String qiymet;

    public ToxumMapper(String toxumName, String toxumSort, String contact, String region, String sellerName, String email, String qiymet) {
        this.toxumName = toxumName;
        this.toxumSort = toxumSort;
        this.contact = contact;
        this.region = region;
        this.sellerName = sellerName;
        this.email = email;
        this.qiymet = qiymet;
    }
    public ToxumMapper(){

    }

    public String getToxumName() {
        return toxumName;
    }

    public void setToxumName(String toxumName) {
        this.toxumName = toxumName;
    }

    public String getToxumSort() {
        return toxumSort;
    }

    public void setToxumSort(String toxumSort) {
        this.toxumSort = toxumSort;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQiymet() {
        return qiymet;
    }

    public void setQiymet(String qiymet) {
        this.qiymet = qiymet;
    }
}
