package com.example.contactapp_v1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    List<Contact> getAllContacts();

    @Insert
    void insertAll(Contact... contact);
}
