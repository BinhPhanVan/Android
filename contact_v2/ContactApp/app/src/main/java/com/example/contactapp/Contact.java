package com.example.contactapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact  implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo
    private String firstname;
    @ColumnInfo
    private String lastname;
    @ColumnInfo
    private String mobile;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private byte[] imgAva;

    public Contact(String firstname, String lastname, String mobile, String email, byte[] imgAva) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
        this.imgAva = imgAva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImgAva() {
        return imgAva;
    }

    public void setImgAva(byte[] imgAva) {
        this.imgAva = imgAva;
    }
}
