package com.example.testgk.viewmodel;

import android.content.Context;

import com.example.testgk.model.DogBreed;
import com.example.testgk.model.DogsApi;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsApiService {
    private static final String BASE_URL = "http://staff.vnuk.edu.vn:5000/";
    private DogsApi api;
    public DogsApiService()
    {
        api =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DogsApi.class);
    }
    private static DogsApiService instance;
    public static DogsApiService getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DogsApiService();
        }
        return instance;
    }
    public Single<List<DogBreed>> getDogs()
    {
        return api.getDogs();
    }
}
