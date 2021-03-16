package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kavak ;)
 */
public class Update {
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("result")
    private UpdatesInfo results;
//    @Expose
//    @SerializedName("errorMsg")
//    private int errorMsg;

//    public void setErrorMsg(int errorMsg) {
//        this.errorMsg = errorMsg;
//    }

    public void setResults(UpdatesInfo results) {
        this.results = results;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public int getErrorMsg() {
//        return errorMsg;
//    }

    public int getStatus() {
        return status;
    }
    public UpdatesInfo getResults() {
        return results;
    }
}
