package com.softteco.roadqualitydetector.rest.dto;

import com.google.gson.annotations.SerializedName;

public class Picture {
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}
