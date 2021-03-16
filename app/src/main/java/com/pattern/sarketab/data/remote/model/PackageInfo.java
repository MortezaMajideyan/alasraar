package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageInfo {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("description")
    String description;
    @Expose
    @SerializedName("price")
    String price;
    @Expose
    @SerializedName("off")
    String off;
    @Expose
    @SerializedName("credit")
    String credit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public PackageInfo(int id, String title, String description, String price, String off, String credit) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.off = off;
        this.credit = credit;
    }
}
