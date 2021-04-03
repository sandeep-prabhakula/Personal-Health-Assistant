package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RecyclerView recyclerView = findViewById(R.id.settingRecycler);
        List<SettingModel> settings = new ArrayList<>();
        SettingModel setting = new SettingModel(R.drawable.ic_baseline_dialpad_24,"Change Password");
        settings.add(setting);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new SettingsAdapter(settings, this);
        recyclerView.setAdapter(adapter);
        TextView textView6 = findViewById(R.id.textView6);
        String mail = textView6.getText().toString().trim();
        String[] email = mail.split("");
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL,email);
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i,"choose an email app"));
            }
        });
    }
}