package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Contact> contacts;
    private ContactAdapter contactAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                Contact tmp = new Contact("Phan Văn Bình", "0974526457", "binh@gmail.com");
//                contactDao.insertAll(tmp);
//            }
//        });
//        Contact c1 = new Contact("Phan Văn Bình", "0974526457", "binh@gmail.com");
//        Contact c2 = new Contact("Lê Văn Huy", "0123654798", "huy@gmail.com");
//        Contact c3 = new Contact("Nguyễn Thanh Cơ", "0857496321", "co@gmail.com");
//        contactDao.insertAll(c2,c3);
        //        contacts = new ArrayList<Contact>();
//        Contact c1 = new Contact("Phan Van Binh", "0974526457", "binh@gmail.com");
//        Contact c2 = new Contact("Le Thanh Quy", "0125478963", "quy@gmail.com");
//        Contact c3 = new Contact("Le Van Huy", "0974526125", "huy@gmail.com");
//        Contact c4 = new Contact("Nguyen Thanh Co", "0974526896", "co@gmail.com");
//        Contact c5 = new Contact("Tran Nguyen Anh Trinh", "0974525524", "gold@gmail.com");
//        contactDao.insertAll(c1, c2, c3, c4, c5);
        loadContactList();
        initRecycleView();


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddActivity.class), 100);
            }
        });

    }
    private void initRecycleView()
    {
        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rvContacts.addItemDecoration(dividerItemDecoration);
        binding.rvContacts.setAdapter(contactAdapter);
    }
    private void loadContactList()
    {
        contacts = (ArrayList<Contact>) contactDao.getAllContacts();
        contactAdapter = new ContactAdapter((ArrayList<Contact>) contactDao.getAllContacts());
//        contactAdapter.SetData(contacts);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode==100)
        {
            String name = data.getStringExtra("name");
            String mobile = data.getStringExtra("mobile");
            String email =  data.getStringExtra("email");
            System.out.println(name + " "+ mobile + " "+ email);
            contactDao.insertAll(new Contact(name, mobile, email));
            loadContactList();
            initRecycleView();
            Toast.makeText(MainActivity.this, "Thêm liên hệ thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm liên hệ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}