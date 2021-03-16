package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kavak ;)
 */
public class UserOutPut {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("pishgoo")
    @Expose
    private int pishgoo;
    @SerializedName("sarketab")
    @Expose
    private int sarketab;

    public int getPishgoo() {
        return pishgoo;
    }

    public void setPishgoo(int pishgoo) {
        this.pishgoo = pishgoo;
    }

    public int getSarketab() {
        return sarketab;
    }

    public void setSarketab(int sarketab) {
        this.sarketab = sarketab;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserOutPut(int id) {
        this.id = id;
    }
}
