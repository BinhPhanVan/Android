package com.midterm.phanvanbinh.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface AddressApi {
    @GET("b/62503b89d8a4cc06909df432")
    Single<List<Address>> getAdds();
}