package com.example.myapplication;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextView rEmail = findViewById(R.id.email);
        Button button2 = findViewById(R.id.button2);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        button2.setOnClickListener(v -> {
            String email = rEmail.getText().toString().trim();
            if(email.isEmpty()){
                rEmail.setError("Email is required");
                rEmail.requestFocus();
                return ;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                rEmail.setError("Please Enter Valid Email");
                rEmail.requestFocus();
                return;
            }
            auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Email sent to Your Email", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Try Again! something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}