package com.example.testgk.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity
public class DogBreed implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @SerializedName("id")
    private int id;
    @ColumnInfo
    @SerializedName("name")
    private String name;
    @ColumnInfo
    @SerializedName("life_span")
    private String lifeSpan;
    @ColumnInfo
    @SerializedName("origin")
    private String origin;
    @ColumnInfo
    @SerializedName("url")
    private String url;

    public DogBreed(int id, String name, String lifeSpan, String origin, String url) {
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
