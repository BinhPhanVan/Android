package com.midterm.btandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView item11;
        LinearLayout item12;

        TextView item21;
        LinearLayout item22;

        TextView item31;
        LinearLayout item32;
        item11 =findViewById(R.id.item11);
        item12 =findViewById(R.id.item12);
        item21 =findViewById(R.id.item21);
        item22 =findViewById(R.id.item22);
        item31 =findViewById(R.id.item31);
        item32 =findViewById(R.id.item32);

        TextView timeday2 = findViewById(R.id.timeday2);
        TextView timeday3 = findViewById(R.id.timeday3);

        TextView vvvv = findViewById(R.id.timeday1);


        String timeStamp = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
        int phut = Integer.parseInt(timeStamp.substring(3, 5));
        int phut1 = Integer.parseInt(vvvv.getText().toString().substring(3, 5));

        item31.setText((phut1-phut)*-1+" phút trước");

        final int[] count = {0};
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(HomeActivity.this, R.style.Dialogs);
                dialog.setContentView(R.layout.layout_option_image);
                TextView them;
                TextView huy;
                EditText tieude;
                EditText mota;
                them = dialog.findViewById(R.id.them);
                tieude = dialog.findViewById(R.id.tieude);
                mota = dialog.findViewById(R.id.mota);

                huy = dialog.findViewById(R.id.huy);

                dialog.show();
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (count[0] ==0){
                            String timeStamp = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
                            timeday2.setText(timeStamp);
                            item21.setVisibility(View.VISIBLE);
                            item22.setVisibility(View.VISIBLE);
                            count[0] = 2;
                        } else {
                            String timeStamp = new SimpleDateFormat("HH:mm aa").format(Calendar.getInstance().getTime());
                            timeday3.setText(timeStamp);
                            item11.setVisibility(View.VISIBLE);
                            item12.setVisibility(View.VISIBLE);
                        }
                        dialog.dismiss();
                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}