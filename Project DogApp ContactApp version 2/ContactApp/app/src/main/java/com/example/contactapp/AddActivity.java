package com.example.contactapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityAddBinding;
import com.example.contactapp.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    private byte[] storeImg;
    final int TAKE_PHOTO = 1;
    final int LIBRARY = 2;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        Contact contact = (Contact) bundle.get("Object_contact");
        binding.tvFirstName.setText(contact.getFirstname());
        binding.tvLastName.setText(contact.getLastname());
        binding.tvPhone.setText(contact.getMobile());
        binding.tvEmail.setText(contact.getEmail());

        binding.btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = binding.tvFirstName.getText().toString();
                String lastname = binding.tvLastName.getText().toString();
                String mobile = binding.tvPhone.getText().toString();
                String email = binding.tvEmail.getText().toString();
                if(firstname.isEmpty() || mobile.isEmpty() || email.isEmpty() || lastname.isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("firstname",firstname);
                            intent.putExtra("lastname",lastname);
                            intent.putExtra("phone",mobile);
                            intent.putExtra("email",email);
                            intent.putExtra("picture",storeImg);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                    });
                    CloseKeyBoard();
                }
            }
        });
        binding.ivTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });
    }
    void select(){
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        TextView title = new TextView(AddActivity.this);
        title.setText("Add Photo");
        title.setBackgroundColor(0xFF0166D5);
        title.setPadding(10, 15, 15, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);


        AlertDialog.Builder builder = new AlertDialog.Builder(
                AddActivity.this);



        builder.setCustomTitle(title);

        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, TAKE_PHOTO);

                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, LIBRARY);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LIBRARY && resultCode == RESULT_OK) {
            if (data != null) {
                binding.ivAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                try {
                    Uri uri = data.getData();
                    binding.ivAvatar.setImageURI(uri);
                    InputStream iStream =  getContentResolver().openInputStream(uri);
                    storeImg = getBytes(iStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getExtras() == null) {
                    return;
                }
                binding.ivAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                binding.ivAvatar.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                storeImg = baos.toByteArray();
            }
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
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
                String firstname = binding.tvFirstName.getText().toString();
                String lastname = binding.tvLastName.getText().toString();
                String mobile = binding.tvPhone.getText().toString();
                String email = binding.tvEmail.getText().toString();
                if(firstname.isEmpty() || mobile.isEmpty() || email.isEmpty() || lastname.isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                            intent.putExtra("firstname",firstname);
                            intent.putExtra("lastname",lastname);
                            intent.putExtra("phone",mobile);
                            intent.putExtra("email",email);
                            intent.putExtra("picture",storeImg);
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