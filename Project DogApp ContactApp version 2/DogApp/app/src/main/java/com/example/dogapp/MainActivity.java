package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dogapp.databinding.ActivityMainBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmodel.DogsAdapter;
import com.example.dogapp.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private DogsApiService apiService;

    private DogsAdapter dogsAdapter;
    private ArrayList<DogBreed> dogBreeds;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        binding.rvDogs.setLayoutManager(new GridLayoutManager(this, 2));
        apiService = new DogsApiService();
        dogBreeds = new ArrayList<DogBreed>();
        dogsAdapter = new DogsAdapter(dogBreeds);

        binding.rvDogs.setAdapter(dogsAdapter);

        apiService.getDogs().subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
            @Override
            public void onSuccess(@NonNull List<DogBreed> dogBreedsList) {
                Log.d("DeBUG1", "SUCCESS");
                for(DogBreed dog:dogBreedsList)
                {
                    dogBreeds.add(dog);
//                    Log.d("DEBUG1", dog.getName());
                    dogsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("DEBUG1", "Fail: " +  e.getMessage());
            }
        });
    }
}