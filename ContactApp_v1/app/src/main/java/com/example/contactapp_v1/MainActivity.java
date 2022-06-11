package com.example.contactapp_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.contactapp_v1.databinding.ActivityMainBinding;

import java.util.ArrayList;

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
        Contact tmp = new Contact("Phan Văn Bình", "0974526457", "binh@gmail.com");
        contactDao.insertAll(tmp);
        contacts = new ArrayList<Contact>();

        contacts.add(new Contact("Phan Văn Bình", "0974526457", "binh@gmail.com"));
        contacts.add(new Contact("Nguyễn Văn A", "0945215631", "a@gmail.com"));
        contacts.add(new Contact("Nguyễn Thị B", "0975862418", "b@gmail.com"));

        contactAdapter = new ContactAdapter(contacts);

        binding.rvContacts.setAdapter(contactAdapter);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}