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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityMainBinding;
import com.example.contactapp.my_interface.IClickItemAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
    private void loadContactList() {
        contacts = (ArrayList<Contact>) contactDao.getAllContacts();
        contactAdapter = new ContactAdapter((ArrayList<Contact>) contactDao.getAllContacts(),
        new IClickItemAdapter() {
            @Override
            public void onClickItemContact(Contact contact) {
                onClickItem(contact);
            }
        });
//        contactAdapter.SetData(contacts);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode==100)
        {
            String firstname = data.getStringExtra("firstname");
            String lastname = data.getStringExtra("lastname");
            String mobile = data.getStringExtra("mobile");
            String email =  data.getStringExtra("email");
            byte[] picture = (byte[]) data.getExtras().get("picture");
            contactDao.insertAll(new Contact(firstname, lastname, mobile, email, picture));
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
    private void onClickItem(Contact contact) {
        Intent intent  = new Intent(MainActivity.this, EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object_contact", (Serializable) contact);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}