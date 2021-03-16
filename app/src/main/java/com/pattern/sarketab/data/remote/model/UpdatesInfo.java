package com.pattern.sarketab.data.remote.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by kavak ;)
 */
public class UpdatesInfo {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("os")
    private String os;
    @SerializedName("version")
    private String version;
    @SerializedName("force")
    private int force;
    @SerializedName("url")
    private String url;


    public UpdatesInfo(int id , String title,String content,String os ,String version, int force,String url){
        this.id = id;
        this.title = title;
        this.content = content;
        this.os = os;
        this.version = version;
        this.force = force;
        this.url = url;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setString(String string) {
        this.title = string;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getForce() {
        return force;
    }

    public String getOs() {
        return os;
    }

    public String getString() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
}
