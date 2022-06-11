package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact ORDER BY firstname ASC")
    List<Contact> getAllContacts();

    @Insert
    void insertAll(Contact... contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM Contact")
    void deleteAllItem();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact findById(int id);

    @Query("UPDATE Contact SET firstName = :fistName, lastName = :lastName, " +
            "mobile = :mobile, email = :email, imgAva =:pic  WHERE id = :id")
    void update(int id, String fistName, String lastName, String mobile, String email, byte[] pic);

}
