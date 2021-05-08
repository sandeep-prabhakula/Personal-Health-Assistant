package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FingerAuth extends AppCompatActivity {
    Handler handler;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_auth);

        handler = new Handler();
        FingerAuth act = this;
        Button tryAgain = findViewById(R.id.tryAgain);

        Executor ex = Executors.newSingleThreadExecutor();
        BiometricPrompt bp = new BiometricPrompt.Builder(this)
                .setTitle("Unlock Healthy Side")
                .setNegativeButton("CANCEL", ex, (dialog, which) -> FingerAuth.super.onBackPressed())
                .build();
        bp.authenticate(new CancellationSignal(), ex, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                act.runOnUiThread(() -> handler.postDelayed(() -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        startActivity(new Intent(FingerAuth.this,MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(FingerAuth.this,Login.class));
                    }
                    finish();
                },100));
            }
        });


        tryAgain.setOnClickListener(v -> bp.authenticate(new CancellationSignal(), ex, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                act.runOnUiThread(() -> handler.postDelayed(() -> {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        startActivity(new Intent(FingerAuth.this,MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(FingerAuth.this,Login.class));
                    }
                    finish();
                },100));
            }
        }));
    }
}