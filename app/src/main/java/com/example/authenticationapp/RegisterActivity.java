package com.example.authenticationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
//    Global Variables for the Layout
    EditText mFullName, mEmailAddress, mPhoneNumber, mPassword;
    Button mRegisterButton;
    TextView mLogin;
    ProgressBar mProgressBar;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        References in the Layout
        mFullName = findViewById(R.id.editTextFullName);
        mEmailAddress = findViewById(R.id.editTextEmailAddress);
        mPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        mPassword = findViewById(R.id.editTextPassword);
        mRegisterButton = findViewById(R.id.buttonRegister);
        mLogin = findViewById(R.id.textViewLogin);
        mProgressBar = findViewById(R.id.progressBarRegister);

//        Create a Firebase instance
        mFirebaseAuth = FirebaseAuth.getInstance();
    }
}