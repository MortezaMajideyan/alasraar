package com.pattern.sarketab.ui.Modle;

import com.google.gson.annotations.SerializedName;

public class M_sign_in {

    @SerializedName("message")
    public String message;

    @SerializedName("id")
    public String id;


    public M_sign_in(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
