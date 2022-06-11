package com.example.testgk.viewmodel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testgk.model.DogBreed;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM DogBreed")
    List<DogBreed> getAllDogBreed();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DogBreed... dogBreeds);

    @Delete
    void delete(DogBreed contact);

    @Query("DELETE FROM DogBreed")
    void deleteAllItem();
}
