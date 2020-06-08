package com.example.authenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
//    Global Layout Variables
    EditText mEmailAddress, mPassword;
    Button loginButton;
    TextView returnToRegister;
    ProgressBar mProgressBar;
//    Global Firebase Variable
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        References to Layout items
        mEmailAddress = findViewById(R.id.textViewLoginEmail);
        mPassword = findViewById(R.id.editTextLoginPassword);
        loginButton = findViewById(R.id.buttonLogin);
        returnToRegister = findViewById(R.id.textViewRegister);
        mProgressBar = findViewById(R.id.progressBarLogin);

//        Instantiate Firebase
        mFirebaseAuth = FirebaseAuth.getInstance();

//        Button onClickListener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Save Edit Text data to string variables
                String email = mEmailAddress.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
//                Validate Email
                if (TextUtils.isEmpty(email)){
                    mEmailAddress.setError("Field cannot be empty!");
                    return;
                }
//                Validate password
                if (!TextUtils.isEmpty(password)){
                    if (password.length()<=5){
                        mPassword.setError("Password is too short!");
                        return;
                    }
                } else{
                    mPassword.setError("Field cannot be empty!");
                    return;
                }
//                If everything checks out, set visibility to Progress Bar
                mProgressBar.setVisibility(View.VISIBLE);
//                Sign in the user
                mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                    "Sign in successful!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Error!" + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        //        Set up on click listener to the text view
        returnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}