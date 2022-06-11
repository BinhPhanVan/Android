package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DogBreed  implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("origin")
    private String origin;

    @SerializedName("url")
    private String url;

    @SerializedName("bred_for")
    private String BredFor;

    @SerializedName("breed_group")
    private String BredGroup;


    public DogBreed(int id, String name, String lifeSpan, String origin, String url, String bredFor, String bredGroup)
    {
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        BredFor = bredFor;
        BredGroup = bredGroup;
    }

    public String getBredGroup() {
        return BredGroup;
    }

    public void setBredGroup(String bredGroup) {
        BredGroup = bredGroup;
    }

    public String getBredFor() {
        return BredFor;
    }

    public void setBredFor(String bredFor) {
        BredFor = bredFor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
