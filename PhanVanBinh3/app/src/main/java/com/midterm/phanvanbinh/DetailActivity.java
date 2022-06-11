package com.midterm.phanvanbinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.midterm.phanvanbinh.databinding.ActivityDetailBinding;
import com.midterm.phanvanbinh.model.Address;

public class DetailActivity extends AppCompatActivity {

    private Address address;
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        address = (Address) getIntent().getSerializableExtra("item_address");

        binding.tvTitle.setText(address.getTitle());
        binding.tvDesc.setText(address.getDesc());
        binding.tvE.setText(address.getE());
        binding.tvTimestamp.setText(address.getTimeStamp());
        binding.tvAddr.setText(address.getAddr());
        binding.tvLng.setText(address.getLng());
        binding.tvLat.setText(address.getLat());
        binding.tvZip.setText(address.getZip());


    }
}