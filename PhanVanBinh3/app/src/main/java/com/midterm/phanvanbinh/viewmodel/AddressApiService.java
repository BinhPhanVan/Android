package com.midterm.phanvanbinh.viewmodel;

import com.midterm.phanvanbinh.model.Address;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressApiService {
    private static final String BASE_URL = "https://api.jsonbin.io/";
    private AddressApi api;

    private static AddressApiService instance;
    private AddressApiService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(AddressApi.class);
    }

    public static AddressApiService getInstance(){
        if(instance == null){
            instance = new AddressApiService();
        }
        return instance;
    }
    public Single<List<Address>> getAddress(){
        return api.getAddress();
    }
}
