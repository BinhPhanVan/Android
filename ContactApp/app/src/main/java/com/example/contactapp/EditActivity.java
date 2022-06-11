package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.contactapp.databinding.ActivityEditBinding;
import com.example.contactapp.databinding.ActivityMainBinding;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Contact contact = (Contact) bundle.get("Object_contact");
        binding.tvNameUser.setText(contact.getName());

    }
}