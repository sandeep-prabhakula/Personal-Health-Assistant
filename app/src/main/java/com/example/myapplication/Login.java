package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText editTextTextPersonName,editTextTextPassword;
    private Button button;
    private TextView textView7,textView16;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.button);
        textView7 = findViewById(R.id.textView7);
        textView16 = findViewById(R.id.textView16);
        auth = FirebaseAuth.getInstance();
        TextView phoneAuth = findViewById(R.id.phoneAuth);
        phoneAuth.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,PhoneAuthentication.class));
            finish();
        });
        button.setOnClickListener(v -> {
            String email = editTextTextPersonName.getText().toString();
            String pass = editTextTextPassword.getText().toString();
            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                Toast.makeText(Login.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
            }
            else{
                login(email,pass);
            }
        });
        textView16.setOnClickListener(v -> startActivity(new Intent(Login.this,ForgotPassword.class)));
        textView7.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,SignUp.class));
            finish();
        });
    }
    private void login(String mail,String pass){
        auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(Login.this, task -> {
            if(task.isSuccessful()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user.isEmailVerified()){
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    user.sendEmailVerification();
                    Toast.makeText(Login.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}