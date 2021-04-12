package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testapp.myrecycler.CustomAdapter;
import com.example.testapp.myrecycler.Model;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false));

        List<Model> list = new ArrayList<>();
        Model model = new Model();
        model.setName("Іван");
        model.setVersion("0.1");
        list.add(model);
        Model model1 = new Model();
        model1.setName("Іванка");
        model1.setVersion("2.1");
        list.add(model1);
        customAdapter = new CustomAdapter(list,  this);
        recyclerView.setAdapter(customAdapter);

    }
}