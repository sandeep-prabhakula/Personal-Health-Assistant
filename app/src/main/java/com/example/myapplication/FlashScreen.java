package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class FlashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_flash_screen);

        new Handler().postDelayed(()->{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null){
                startActivity(new Intent(FlashScreen.this,FingerAuth.class));
            }
            else {
                startActivity(new Intent(FlashScreen.this,Login.class));
            }
            finish();
        },1500);
    }
}