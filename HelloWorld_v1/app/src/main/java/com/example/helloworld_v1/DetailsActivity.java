package com.example.helloworld_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld_v1.databinding.ActivityDetailsBinding;
import com.example.helloworld_v1.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {
    String index;
    String data;
    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        Intent intent = getIntent();
        if( intent != null )
        {
            data = intent.getStringExtra("number");
            index = intent.getStringExtra("index");

            binding.editValue.setText("" + data);
        }
        binding.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                intent.putExtra("newValue", binding.editValue.getText().toString());
                intent.putExtra("index", index);
                //System.out.print(index + " " + binding.editValue.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}