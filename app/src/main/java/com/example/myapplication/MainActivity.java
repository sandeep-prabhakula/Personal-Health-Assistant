package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        ImageView signOut = findViewById(R.id.signOut);
        auth  = FirebaseAuth.getInstance();
        signOut.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
        });
        ImageView settings = findViewById(R.id.settings);
        settings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,Settings.class)));
        List<ModelClass> activities = new ArrayList<>();
        ModelClass act = new ModelClass(R.drawable.img,"Fitness");
        activities.add(act);
        ModelClass act1 = new ModelClass(R.drawable.img2,"Diagnosis");
        activities.add(act1);
        ModelClass act2 = new ModelClass(R.drawable.img3,"Diet");
        activities.add(act2);
        ModelClass act3 = new ModelClass(R.drawable.img8,"BMI Calculator");
        activities.add(act3);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new MainAdapter(activities);
        recyclerView.setAdapter(adapter);

    }
}