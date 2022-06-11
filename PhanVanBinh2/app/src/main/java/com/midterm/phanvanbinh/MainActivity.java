package com.midterm.phanvanbinh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.midterm.phanvanbinh.databinding.ActivityMainBinding;
import com.midterm.phanvanbinh.model.Address;
import com.midterm.phanvanbinh.viewmodel.AddressAdapter;
import com.midterm.phanvanbinh.viewmodel.AddressApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private AddressApiService apiService;

    private AddressAdapter addsAdapter;
    private ArrayList<Address> addList;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        binding.rvAdds.setLayoutManager(new GridLayoutManager(this, 2));
        apiService = new AddressApiService();
        addList = new ArrayList<Address>();
        addsAdapter = new AddressAdapter(addList);

        binding.rvAdds.setAdapter(addsAdapter);

        apiService.getAdds().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Address>>() {
                    @Override
                    public void onSuccess(@NonNull List<Address> addressList) {
                        Log.d("DeBUG1", "SUCCESS");
//                        for(Address add:AddLists)
//                        {
//                            dogBreeds.add(add);
//                            Log.d("DEBUG1", add.getTitle());
//                            dogsAdapter.notifyDataSetChanged();
//                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("AAAAAA");
                        Log.d("DEBUG1", "Fail: " +  e.getMessage());
                    }
                });
    }
}