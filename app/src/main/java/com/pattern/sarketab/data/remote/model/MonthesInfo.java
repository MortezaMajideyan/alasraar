package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AhmadKing ;)
 *
 * @soundtrack Nagoo Nagofti (www.Next1.ir) - Mehdi Yarrahi (www.Next1.ir)
 */
public class MonthesInfo {

    @Expose
    @SerializedName("id")
    int id;
    @Expose
    @SerializedName("title")
    String title;
    @Expose
    @SerializedName("description")
    String description;


    public MonthesInfo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

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
}
