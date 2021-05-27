package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Diagnosis extends AppCompatActivity {
    String api_url = "";
    TextView symptom,textView17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        symptom = findViewById(R.id.symptom);
        textView17 = findViewById(R.id.textView17);
        Button getSymptom = findViewById(R.id.getSymptom);
        getSymptom.setOnClickListener(v -> postData());
    }

    private void postData() {
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST, api_url, response -> {
                //get response
        }, error -> Log.d("rale",error.toString())){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> map = new HashMap<>();
                map.put("symptom",symptom.getText().toString());
                return map;
            }
        };
        rq.add(sr);
    }
}