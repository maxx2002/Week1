package com.example.week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.ListData;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton main_floatingActionButton;
    private RecyclerView main_RecyclerView;
    private TextView main_text_nodata;
    private DataAdapter adapter;
    public ArrayList<ListData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_RecyclerView.setLayoutManager(manager);
        main_RecyclerView.setAdapter(adapter);

        main_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditUser.class);
                intent.putExtra("condition", "add");
                intent.putParcelableArrayListExtra("arraylist", list);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        main_RecyclerView = findViewById(R.id.main_RecyclerView);
        main_floatingActionButton = findViewById(R.id.main_floatingActionButton);
        main_text_nodata = findViewById(R.id.main_text_nodata);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("arraylist");
        if (list == null) {
            list = new ArrayList<>();
        }
        adapter = new DataAdapter(list);

        if (list.size() != 0) {
            main_text_nodata.setVisibility(View.GONE);
        } else {
            main_text_nodata.setVisibility(View.VISIBLE);
        }
        Log.d("cek", String.valueOf(list.size()));
    }
}