package com.midterm.phanvanbinh.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Address.class}, version = 1)
public abstract class AddressDatabase extends RoomDatabase {
    public abstract AddressDao addressDao();

    private static AddressDatabase instance;
    public static AddressDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, AddressDatabase.class, "address_database").build();
        }
        return instance;
    }
}
