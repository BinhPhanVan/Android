package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.contactapp.databinding.ActivityEditBinding;
import com.example.contactapp.databinding.ActivityMainBinding;

import java.io.Serializable;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Contact contact = (Contact) bundle.get("Object_contact");
        binding.tvNameUser.setText(contact.getFirstname().toString()+ contact.getLastname().toString());
        binding.tvPhone.setText(contact.getMobile());

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_edit)
        {
            Contact contact = (Contact) bundle.get("Object_contact");
            Intent intent  = new Intent(EditActivity.this, AddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Object_contact", (Serializable) contact);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}