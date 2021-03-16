package com.pattern.sarketab.data.local.models;

/**
 * Created by kavak ;)
 */
public class Details {

    public Details(int id,String content){
        this.id = id;
        this.content = content;
    }
    int id;
    String content;

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
