package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculation extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker);
        EditText height = findViewById(R.id.height);
        EditText weight = findViewById(R.id.weight);
        Button getBMI = findViewById(R.id.getBMI);
        TextView result = findViewById(R.id.result);
        ImageView BMIChart = findViewById(R.id.BMIChart);
        getBMI.setOnClickListener(v -> {
            String h = height.getText().toString().trim();
            String w = weight.getText().toString().trim();
            double l = Double.parseDouble(h)*0.01;
            double m = Double.parseDouble(w);
            double bmi = m/(l*l);
            String res = "BMI is : "+bmi+" for more details check the the chart below";
            result.setText(res);
        });
    }
}