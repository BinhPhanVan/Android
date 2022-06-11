package com.midterm.phanvanbinh.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

@Dao
public interface AddressDao {
    @Query("SELECT * FROM Address")
    List<Address> getAllAddress();



    @Insert
    void insertAll(@NonNull List<Address> addressList);
}
