package com.midterm.phanvanbinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.midterm.phanvanbinh.model.Address;
import com.midterm.phanvanbinh.model.AddressDao;
import com.midterm.phanvanbinh.model.AddressDatabase;
import com.midterm.phanvanbinh.viewmodel.AddressApiService;
import com.midterm.phanvanbinh.viewmodel.AddressAdapter;
import com.midterm.phanvanbinh.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ActivityMainBinding binding;
    private ArrayList<Address> listAddress;
    private AddressAdapter listAddressAdapter;
    private AddressDatabase appDatabase;
    private AddressDao addressDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        listAddress = new ArrayList<>();
        listAddressAdapter = new AddressAdapter(listAddress , this);
        binding.listAddress.setLayoutManager(new LinearLayoutManager(this ));
        binding.listAddress.setAdapter(listAddressAdapter);

        appDatabase = AddressDatabase.getInstance(this);
        addressDao = appDatabase.addressDao();


        AddressApiService.getInstance().getAddress()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Address>>() {
                    @Override
                    public void onSuccess(@NonNull List<Address> addressList) {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    addressDao.insertAll(addressList);
                                } catch (Exception e){

                                }
                            }
                        });

                        listAddress.addAll(addressList);
                        listAddressAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập địa chỉ tìm kiếm");
        searchView.setOnQueryTextListener(this);

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        AddressApiService.getInstance().getAddress()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Address>>() {
                    @Override
                    public void onSuccess(@NonNull List<Address> addressList) {
                        listAddress.clear();
                        ArrayList<Address> list_address_search = new ArrayList<>();

                        for (Address i: addressList) {
                            if(i.getTitle().toLowerCase().trim().contains(newText.toLowerCase().trim())){
                                list_address_search.add(i);
                            }
                        }

                        listAddress.addAll(list_address_search);
                        listAddressAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
        return true;
    }
}