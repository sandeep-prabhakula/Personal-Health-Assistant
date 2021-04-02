package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextView rEmail = findViewById(R.id.email);
        Button button2 = findViewById(R.id.button2);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this, "Email sent to Your Email", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ForgotPassword.this, "Try Again! something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}