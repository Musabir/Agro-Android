package com.example.musabir.agro.Mapper;

/**
 * Created by Musabir on 11/25/2017.
 */
public class HesablaMapper {

    private String xidmet;
    private int icon;
    private String qiymet;
    private boolean selected;
    private int sahe;
    private String size;

    public HesablaMapper(String xidmet, int icon, String qiymet, boolean selected, int sahe) {
        this.xidmet = xidmet;
        this.icon = icon;
        this.qiymet = qiymet;
        this.selected = selected;
        this.sahe = sahe;
    }
    public HesablaMapper(){}
    public String getXidmet() {
        return xidmet;
    }

    public void setXidmet(String xidmet) {
        this.xidmet = xidmet;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getQiymet() {
        return qiymet;
    }

    public void setQiymet(String qiymet) {
        this.qiymet = qiymet;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getSahe() {
        return sahe;
    }

    public void setSahe(int sahe) {
        this.sahe = sahe;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}