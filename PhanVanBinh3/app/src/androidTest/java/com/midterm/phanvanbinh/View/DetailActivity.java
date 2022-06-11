package com.midterm.phanvanbinh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.midterm.phanvanbinh.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    Address address;
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        address = (Address) getIntent().getSerializableExtra("address");

        binding.addrAddress.setText(address.getAddr());
        binding.titleAddress.setText(address.getTitle());
        binding.timestampAddress.setText(address.getTimeStamp());
        binding.descAddress.setText(address.getDesc());
        binding.eAddress.setText(address.getE());
        binding.zipAddress.setText(address.getZip());
        binding.lngAddress.setText(address.getLng());
        binding.latAddress.setText(address.getLat());

    }
}