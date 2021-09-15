package com.example.week1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import Model.ListData;

public class EditUser extends AppCompatActivity {

    private TextInputLayout edit_textInputLayout_nama, edit_textInputLayout_alamat, edit_textInputLayout_umur;
    private Button edit_button_update;
    private ImageView edit_image_back;
    private TextView edit_text_title;
    private Boolean name = false, age = false, address = false;

    public ArrayList<ListData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initView();

        edit_button_update.setEnabled(false);

        final LoadingDialog loadingDialog = new LoadingDialog(EditUser.this);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("arraylist");

        if (list == null) {
            list = new ArrayList<>();
        }
        int position = intent.getIntExtra("position", -1);
        String condition = intent.getStringExtra("condition");

        edit_image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (condition.equalsIgnoreCase("edit")) {
            edit_button_update.setEnabled(true);

            edit_button_update.setText("Update Data");
            edit_text_title.setText("Edit User");

            edit_textInputLayout_nama.getEditText().setText(list.get(position).getNama());
            edit_textInputLayout_umur.getEditText().setText(list.get(position).getUmur());
            edit_textInputLayout_alamat.getEditText().setText(list.get(position).getAlamat());

            edit_button_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama = edit_textInputLayout_nama.getEditText().getText().toString().trim();
                    String umur = edit_textInputLayout_umur.getEditText().getText().toString().trim();
                    String alamat = edit_textInputLayout_alamat.getEditText().getText().toString().trim();

                    ListData temp = new ListData(nama, umur, alamat);

                    list.set(position, temp);

                    loadingDialog.startLoadingAnimation();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                            Intent intent = new Intent(EditUser.this, MainActivity.class);
                            intent.putExtra("arraylist", list);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);
                }
            });
        } else if (condition.equalsIgnoreCase("add")) {
            edit_button_update.setText("Add Data");
            edit_text_title.setText("Add User");
            edit_button_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama = edit_textInputLayout_nama.getEditText().getText().toString().trim();
                    String umur = edit_textInputLayout_umur.getEditText().getText().toString().trim();
                    String alamat = edit_textInputLayout_alamat.getEditText().getText().toString().trim();

                    ListData temp = new ListData(nama, umur, alamat);

                    list.add(temp);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("arraylist", list);
                    startActivity(intent);

                    loadingDialog.startLoadingAnimation();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                            finish();
                        }
                    }, 2000);
                }
            });

            edit_textInputLayout_nama.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String nama = edit_textInputLayout_nama.getEditText().getText().toString().trim();

                    if (!nama.isEmpty()) {
                        name = true;
                    }

                    if (name == true && age == true && address == true) {
                        edit_button_update.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            edit_textInputLayout_alamat.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String alamat = edit_textInputLayout_alamat.getEditText().getText().toString().trim();

                    if (!alamat.isEmpty()) {
                        address = true;
                    }

                    if (name == true && age == true && address == true) {
                        edit_button_update.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            edit_textInputLayout_umur.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String umur = edit_textInputLayout_umur.getEditText().getText().toString().trim();

                    if (!umur.isEmpty()) {
                        age = true;
                    }

                    if (name == true && age == true && address == true) {
                        edit_button_update.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private void initView() {
        edit_textInputLayout_nama = findViewById(R.id.edit_textInputLayout_nama);
        edit_textInputLayout_alamat = findViewById(R.id.edit_textInputLayout_alamat);
        edit_textInputLayout_umur = findViewById(R.id.edit_textInputLayout_umur);
        edit_button_update = findViewById(R.id.edit_button_update);
        edit_image_back = findViewById(R.id.edit_image_back);
        edit_text_title = findViewById(R.id.edit_text_title);
    }
}