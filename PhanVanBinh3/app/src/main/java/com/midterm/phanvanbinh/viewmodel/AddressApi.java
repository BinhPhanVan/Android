package com.midterm.phanvanbinh.viewmodel;

import com.midterm.phanvanbinh.model.Address;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface AddressApi {
    @GET("b/625039f3d20ace068f9580fb")
    public Single<List<Address>> getAddress();
}
