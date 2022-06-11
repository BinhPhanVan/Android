package com.example.testgk.viewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testgk.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract ContactDao contactDao();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "breeds"
                    ).allowMainThreadQueries().build();
        }
        return instance;
    }
}
