package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AhmadKing ;)
 *
 * @soundtrack Nagoo Nagofti (www.Next1.ir) - Mehdi Yarrahi (www.Next1.ir)
 */
public class Month {
    @Expose
    @SerializedName("status")
    int status ;
    @Expose
    @SerializedName("result")
    MonthResult monthesInfos;
//    @Expose
//    @SerializedName("errorMsg")
//    String errorMsg;


    public Month(int status, MonthResult monthesInfos, String errorMsg) {
        this.status = status;
        this.monthesInfos = monthesInfos;
//        this.errorMsg = errorMsg;
    }
    public Month(){

    }
    public int getStatus() {
        return status;
    }

    public MonthResult getMonthesInfos() {
        return monthesInfos;
    }

//    public String getErrorMsg() {
//        return errorMsg;
//    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMonthesInfos(MonthResult monthesInfos) {
        this.monthesInfos = monthesInfos;
    }

//    public void setErrorMsg(String errorMsg) {
//        this.errorMsg = errorMsg;
//    }
}
