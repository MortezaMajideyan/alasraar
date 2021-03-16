package com.pattern.ahmadpay.models.pay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kavak ;)
 */
public class Url {
    @Expose
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
