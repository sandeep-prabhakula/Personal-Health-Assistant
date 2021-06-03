package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        SettingModel setting1 = new SettingModel(R.drawable.ic_baseline_people_24,"Invite Friends");
        settings.add(setting1);
        SettingModel setting2 = new SettingModel(R.drawable.ic_baseline_language_24,"Change Language");
        settings.add(setting2);
        SettingModel setting3 = new SettingModel(R.drawable.ic_baseline_delete_24,"Delete Account");
        settings.add(setting3);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new SettingsAdapter(settings);
        recyclerView.setAdapter(adapter);
        TextView textView6 = findViewById(R.id.textView6);
        String mail = textView6.getText().toString().trim();
//        String[] email = mail.split("");
        textView6.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_EMAIL,new String[]{mail});
            i.setType("text/plain");
            startActivity(Intent.createChooser(i,"choose an email app"));
        });
    }
}