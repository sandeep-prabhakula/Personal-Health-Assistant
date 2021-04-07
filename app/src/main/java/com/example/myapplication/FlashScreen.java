package com.example.myapplication;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class FlashScreen extends AppCompatActivity {
    Handler handler;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_flash_screen);

        handler = new Handler();
        FlashScreen act = this;
        Executor ex = Executors.newSingleThreadExecutor();
        BiometricPrompt bp = new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Authentication")
                .setNegativeButton("CANCEL", ex, (dialog, which) -> FlashScreen.super.onBackPressed())
                .build();
        bp.authenticate(new CancellationSignal(), ex, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                act.runOnUiThread(() -> handler.postDelayed(() -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        startActivity(new Intent(FlashScreen.this,MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(FlashScreen.this,Login.class));
                    }
                    finish();
                },1500));
            }
        });
    }
}