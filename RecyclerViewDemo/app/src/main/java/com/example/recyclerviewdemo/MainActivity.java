package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

/**
 * Initialize a recyclerview with layout manager
 * and create an instance of adapter
 * and assign the instance to recyclerview object
 * and uses linearlayout manager option
 */
public class MainActivity extends AppCompatActivity {

    // Create three instance variables
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to go recyclerview
        recyclerView = findViewById(R.id.recycler_view);
        // Set layoutManager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }
}