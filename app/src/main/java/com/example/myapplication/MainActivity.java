package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
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
        if (!isConnected()){
            showCustomDialog();
        }
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
        ModelClass act4 = new ModelClass(R.drawable.ic_baseline_local_hospital_24,"Hospitals Nearby");
        activities.add(act4);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new MainAdapter(activities);
        recyclerView.setAdapter(adapter);

    }
    private void showCustomDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_error_24)
                .setTitle("Error")
                .setMessage("No Internet Connection")
                .setCancelable(false)
                .show();
    }
    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificonn = cm.getActiveNetworkInfo();
        NetworkInfo mobileconn = cm.getActiveNetworkInfo();
        return (wificonn != null && wificonn.isConnected()) || (mobileconn != null && mobileconn.isConnected());
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit")
                .setMessage("do you want to exit ?")
        .setPositiveButton("YES", (dialog, which) -> MainActivity.super.onBackPressed())
        .setNegativeButton("NO",null)
        .show();
    }
}