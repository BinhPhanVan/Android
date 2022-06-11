package com.midterm.phanvanbinh.viewmodel;

import android.content.Context;

import com.midterm.phanvanbinh.model.Address;
import com.midterm.phanvanbinh.model.AddressApi;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class AddressApiService {
//    private static final String BASE_URL = "https://api.jsonbin.io/";
//    private AddressApi api;
//    public AddressApiService()
//    {
//        api =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .build()
//                .create(AddressApi.class);
//    }
//    private static AddressApiService instance;
//    public static AddressApiService getInstance(Context context)
//    {
//        if(instance == null)
//        {
//            instance = new AddressApiService();
//        }
//        return instance;
//    }
//    public Single<List<Address>> getAdds()
//    {
//        return api.getAdds();
//    }
//}
public class AddressApiService {
    private static final String BASE_URL = "https://api.jsonbin.io/";
    private AddressApi api;
    public AddressApiService()
    {
        api =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(AddressApi.class);
    }
    private static AddressApiService instance;
    public static AddressApiService getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new AddressApiService();
        }
        return instance;
    }
    public Single<List<Address>> getAdds()
    {
        return api.getAdds();
    }
}

