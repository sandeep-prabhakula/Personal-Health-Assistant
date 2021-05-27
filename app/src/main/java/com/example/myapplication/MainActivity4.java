package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        RecyclerView recyclerView = findViewById(R.id.dietView);
        List<DietClass> diets = new ArrayList<>();
        DietClass diet = new DietClass("Diabetes");
        diets.add(diet);
        DietClass diet1 = new DietClass("Allergies");
        diets.add(diet1);
        DietClass diet2 = new DietClass("Cancer");
        diets.add(diet2);
        DietClass diet3 = new DietClass("Alzeheimers-Disease");
        diets.add(diet3);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new DietAdapter(diets);
        recyclerView.setAdapter(adapter);
    }
}