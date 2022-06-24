package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityAddBinding;
import com.example.contactapp.databinding.ActivityMainBinding;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.tvFirstName.getText().toString() +" "+ binding.tvLastName.getText().toString();
                String mobile = binding.tvPhone.getText().toString();
                String email = binding.tvEmail.getText().toString();
                if(name.isEmpty() || mobile.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("name",name);
                            intent.putExtra("phone",mobile);
                            intent.putExtra("email",email);
                            System.out.print("hello" + name);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                    });
                    CloseKeyBoard();
                }
            }
        });
    }
    private void CloseKeyBoard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        MenuItem menuItem = menu.findItem(R.id.action_save);
        Button btn = (Button) menuItem.getActionView();
        btn.setText("SAVE");
        btn.setBackgroundColor(2);
        btn.setTextSize(20);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.tvFirstName.getText().toString() +" "+ binding.tvLastName.getText().toString();
                String mobile = binding.tvPhone.getText().toString();
                String email = binding.tvEmail.getText().toString();
                if(name.isEmpty() || mobile.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("name",name);
                            intent.putExtra("phone",mobile);
                            intent.putExtra("email",email);
                            System.out.print("hello" + name);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                    });
                    CloseKeyBoard();
                }
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}