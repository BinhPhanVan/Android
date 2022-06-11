package com.midterm.phanvanbinh.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Address implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    @ColumnInfo
    private String desc;

    @SerializedName("timeStamp")
    @ColumnInfo
    private String timeStamp;

    @SerializedName("lat")
    @ColumnInfo
    private String lat;

    @SerializedName("lng")
    @ColumnInfo
    private String lng;

    @SerializedName("addr")
    @ColumnInfo
    private String addr;

    @SerializedName("e")
    @ColumnInfo
    private String e;

    @SerializedName("zip")
    @ColumnInfo
    private String zip;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}