package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayPackage {
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("result")
    private PackageInfo results;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PackageInfo getResults() {
        return results;
    }

    public void setResults(PackageInfo results) {
        this.results = results;
    }
}
