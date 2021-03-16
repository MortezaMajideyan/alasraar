package com.pattern.ahmadpay.models.pay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kavak ;)
 */
public class PayData {
    @Expose
    @SerializedName("status")
    private int status;
    @Expose
    @SerializedName("result")
    private Url url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
