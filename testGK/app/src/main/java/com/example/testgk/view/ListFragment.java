package com.example.testgk.view;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.testgk.R;
import com.example.testgk.model.DogBreed;
import com.example.testgk.viewmodel.AppDatabase;
import com.example.testgk.viewmodel.ContactDao;
import com.example.testgk.viewmodel.DogsAdapter;
import com.example.testgk.viewmodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogsApiService apiService;

    private DogsAdapter dogsAdapter;
    private ArrayList<DogBreed> dogBreeds;
    private RecyclerView rvDogs;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search name dog");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dogsAdapter.getFilter().filter(query);;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dogsAdapter.getFilter().filter(newText);;
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDogs = view.findViewById(R.id.rv_dogs);
        apiService = new DogsApiService();
        dogBreeds = new ArrayList<DogBreed>();
        dogsAdapter = new DogsAdapter(dogBreeds);
        appDatabase = AppDatabase.getInstance(getContext());
        contactDao = appDatabase.contactDao();
        rvDogs.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvDogs.setAdapter(dogsAdapter);

        apiService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreedsList) {
                        Log.d("DeBUG1", "SUCCESS");
                        for(DogBreed dog:dogBreedsList)
                        {
                            dogBreeds.add(dog);
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    contactDao.insertAll(dog);
                                }
                            });

                            dogsAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        dogBreeds.addAll(contactDao.getAllDogBreed());
                        dogsAdapter.notifyDataSetChanged();
                        Log.d("DEBUG1", "Fail: " +  e.getMessage());
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}