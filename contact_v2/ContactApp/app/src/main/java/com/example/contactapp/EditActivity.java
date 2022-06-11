package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityEditBinding;
import com.example.contactapp.databinding.ActivityMainBinding;

import java.io.Serializable;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    private AppDatabase appDatabase;
    private ContactDao contactDao;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        bundle = getIntent().getExtras();
        if(bundle != null)
        {
            Contact contact = (Contact) bundle.get("Object_contact");
            binding.tvNameUser.setText(contact.getFirstname()+" "+ contact.getLastname());
            binding.tvPhone.setText(contact.getMobile());
            byte[] bitmapdata = contact.getImgAva();
            if(bitmapdata!=null){
                binding.ivAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
                binding.ivAvatar.setImageBitmap(bitmap);
            }
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_edit)
        {
            Contact contact = (Contact) bundle.get("Object_contact");
            if (contact == null)
            {
                contact = (Contact) bundle.get("Object_contact");
            }
            Intent intent  = new Intent(EditActivity.this, AddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Object_contact", (Serializable) contact);
            intent.putExtras(bundle);
            startActivityForResult(intent, 99);
        }
        if(item.getItemId() == R.id.action_delete)
        {
            appDatabase = AppDatabase.getInstance(EditActivity.this);
            contactDao = appDatabase.contactDao();
            Contact contact = (Contact) bundle.get("Object_contact");
            contactDao.delete(contact);
            Intent intent  = new Intent(EditActivity.this, MainActivity.class);
            startActivityForResult(intent, -100);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode==10)
        {
            Toast.makeText(EditActivity.this, "Chỉnh sửa liên hệ thành công", Toast.LENGTH_SHORT).show();
        }
    }
}