package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText editTextTextEmailAddress,editTextTextPassword2,editTextTextPassword3;
    private Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView textView13 = findViewById(R.id.textView13);
        textView13.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this,Login.class));
            finish();
        });
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPassword3 = findViewById(R.id.editTextTextPassword3);
        button5 = findViewById(R.id.button5);
        auth = FirebaseAuth.getInstance();
        button5.setOnClickListener(v -> {
            String email = editTextTextEmailAddress.getText().toString();
            String password = editTextTextPassword2.getText().toString();
            String conf = editTextTextPassword3.getText().toString();
            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(conf)){
                Toast.makeText(SignUp.this, "Credentials are empty", Toast.LENGTH_SHORT).show();
            }
            else if(!conf.matches(password)){
                Toast.makeText(SignUp.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            }
            else if(password.length()<8){
                Toast.makeText(SignUp.this, "Password is too small", Toast.LENGTH_SHORT).show();
            }
            else{
                signup(email,password);
            }
        });
    }
    private void signup(String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, task -> {
            if(task.isSuccessful()){
                Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUp.this,Login.class));
                finish();
            }
            else{
                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}