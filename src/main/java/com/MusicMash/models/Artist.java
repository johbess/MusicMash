package com.MusicMash.models;


import net.minidev.json.JSONObject;

import java.util.List;


public class Artist {
    private String mbid;
    private JSONObject result;

    public Artist(String mbid) {
        this.mbid = mbid;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
