package com.example.helloworld_v1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.helloworld_v1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel model;
    private ListViewModel list;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList
        );

        binding.lvCount.setAdapter(arrayAdapter);

        model  = new ViewModelProvider(this).get(MyViewModel.class);

        list = new ViewModelProvider(this).get(ListViewModel.class);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                binding.tvCounter.setText(""+integer);
//                list.join(""+integer);
                arrayList.add(""+integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        list.getList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                arrayList.clear();
                System.out.println("Values: ");
                for(int i=0;i<strings.size();i++)
                {
                    arrayList.add(strings.get(i));
                    System.out.println("Values: " + i);
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
        binding.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count = Integer.parseInt(binding.tvCounter.getText().toString());
//                binding.tvCounter.setText(""  + ++count);
                model.increaseNumber();
                list.join(binding.tvCounter.getText().toString());
            }
        });
        binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int count = Integer.parseInt(binding.tvCounter.getText().toString());
//                binding.tvCounter.setText(""+ --count);
                model.decreaseNumber();
                list.join(binding.tvCounter.getText().toString());
            }
        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.remove(i);
                arrayAdapter.notifyDataSetChanged();
                list.remove(i);
                return false;
            }
        });
        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("number", arrayList.get(i));
                intent.putExtra("index", String.valueOf(i));
                resultLauncher.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                String index = result.getData().getStringExtra("index");
                String dataDetail = result.getData().getStringExtra("newValue");
                list.update(Integer.parseInt(index),dataDetail);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    });
}