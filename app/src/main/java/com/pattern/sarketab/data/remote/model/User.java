package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by kavak ;)
 */
public class User {
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("result")
    private UserOutPut results;
//    @Expose
//    @SerializedName("errorMsg")
//    private int errorMsg;

//    public void setErrorMsg(int errorMsg) {
//        this.errorMsg = errorMsg;
//    }

    public void setResults(UserOutPut results) {
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
    public UserOutPut getResults() {
        return results;
    }

}
