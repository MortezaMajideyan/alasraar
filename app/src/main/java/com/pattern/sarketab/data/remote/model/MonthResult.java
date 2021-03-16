package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kavak ;)
 */
public class MonthResult {

    @Expose
    @SerializedName("updated_at")
    String updated_at;
    @Expose
    @SerializedName("months")
    List<MonthesInfo> monthesInfos;


    public MonthResult(String updated_at, List<MonthesInfo> monthesInfos) {
        this.updated_at = updated_at;
        this.monthesInfos = monthesInfos;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<MonthesInfo> getMonthesInfos() {
        return monthesInfos;
    }

    public void setMonthesInfos(List<MonthesInfo> monthesInfos) {
        this.monthesInfos = monthesInfos;
    }
}
