package com.example.layoutmanagertwonumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


import com.example.layoutmanagertwonumber.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NumberViewModel number;
    private ListViewModel list;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String>  arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList  = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, arrayList
        );

        binding.lvHistory.setAdapter(arrayAdapter);

        number = new ViewModelProvider(this).get(NumberViewModel.class);
        list = new ViewModelProvider(this).get(ListViewModel.class);

        number.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.valueA.setText(""+integer);
                binding.valueB.setText(""+ integer);
            }
        });
        list.getList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                arrayList.clear();
                for(int i=0;i< strings.size();i++)
                {
                    arrayList.add(strings.get(i));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int a = Integer.valueOf(binding.valueA.getText().toString());
                    int b = Integer.valueOf(binding.valueB.getText().toString());
                    list.join(a + " + " + b + " = "+ (a + b));
                }
                catch (Exception e)
                {
                    list.join("Sai dữ liệu");
                }

            }
        });
        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int a = Integer.valueOf(binding.valueA.getText().toString());
                    int b = Integer.valueOf(binding.valueB.getText().toString());
                    list.join(a + " - " + b + " = "+ (a - b));
                }
                catch (Exception e)
                {
                    list.join("Sai dữ liệu");
                }
            }
        });
        binding.btnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int a = Integer.valueOf(binding.valueA.getText().toString());
                    int b = Integer.valueOf(binding.valueB.getText().toString());
                    list.join(a + " * " + b + " = "+ a * b);
                }
                catch (Exception e)
                {
                    list.join("Sai dữ liệu");
                }
            }
        });
        binding.btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int a = Integer.valueOf(binding.valueA.getText().toString());
                    int b = Integer.valueOf(binding.valueB.getText().toString());
                    list.join(a + " / " + b + " = "+ Float.valueOf(a / b));
                }
                catch (Exception e)
                {
                    list.join("Sai dữ liệu");
                }
            }
        });
    }

}