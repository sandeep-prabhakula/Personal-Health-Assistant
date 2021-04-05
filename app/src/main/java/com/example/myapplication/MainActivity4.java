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
        RecyclerView.Adapter adapter = new DietAdapter(diets, this);
        recyclerView.setAdapter(adapter);
//        textView11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://www.google.com/aclk?sa=l&ai=DChcSEwj77pnQoa_vAhUB2JYKHYUrCRUYABAAGgJ0bA&ae=2&sig=AOD64_2SvOS6eONUr0zVAlJvH8GHMzxoBw&q&adurl&ved=2ahUKEwjd0ZHQoa_vAhUTzTgGHefeCdAQ0Qx6BAgYEAE";
//                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                startActivity(i1);
//            }
//        });
//        textView12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiersmaqK_vAhUBwjgGHfyoC80QFjACegQIChAD&url=https%3A%2F%2Ftimesofindia.indiatimes.com%2Flife-style%2Fhealth-fitness%2Fdiet%2Ftop-12-foods-for-brain-and-nervous-system%2Farticleshow%2F20725412.cms&usg=AOvVaw28nMk9vnFpPdUF-Ke2zaG5";
//                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                startActivity(i2);
//            }
//        });
//
//        textView13.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwik7feDp6_vAhVzwTgGHVs4DtIQFjABegQIAhAD&url=https%3A%2F%2Fwww.healthline.com%2Fhealth%2Fheart-health%2Ffood-eat-avoid&usg=AOvVaw23ZqQHBvcfYS-yXkN4-BSE";
//                Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                startActivity(i3);
//            }
//        });
//        textView14.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiMi-icp6_vAhXSyDgGHcoXB8sQFjABegQIARAD&url=https%3A%2F%2Fwww.webmd.com%2Fhypertension-high-blood-pressure%2Fguide%2Fdash-diet&usg=AOvVaw3k7Nsb35YeKqkK4nPIarXT";
//                Intent i4 = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                startActivity(i4);
//            }
//        });
//        textView15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwik_ofTp6_vAhXNG7kGHXYQC8oQFjACegQIARAD&url=https%3A%2F%2Fwww.lancastergeneralhealth.org%2Fhealth-hub-home%2F2020%2Fmarch%2Fwhat-athletes-should-eat-back-to-the-basic-food-groups&usg=AOvVaw1vjzBAqtwOZCTDB_AspnQP";
//                Intent i5 = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
//                startActivity(i5);
//            }
//        });
    }
}