package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.fitnessRecycler);
        List<FitnessModel>exercises = new ArrayList<>();
        FitnessModel exercise = new FitnessModel(R.drawable.img10,"Core ABS");
        exercises.add(exercise);
        FitnessModel exercise1 = new FitnessModel(R.drawable.img7,"Lats");
        exercises.add(exercise1);
        FitnessModel exercise2 = new FitnessModel(R.drawable.img9,"Biceps");
        exercises.add(exercise2);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new FitnessAdapter(exercises, this);
        recyclerView.setAdapter(adapter);
    }
}