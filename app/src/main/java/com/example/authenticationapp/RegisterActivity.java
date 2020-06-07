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

//        Check if the user is already logged in/ returning user - Redirect to the MainActivity
        if (mFirebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
//        Set up an onClick Listener on the Register Button
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Find the necessary fields for Email and Password Authentication
                String email = mEmailAddress.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
//                Validate the Email passed
                if(TextUtils.isEmpty(email)){
                    mEmailAddress.setError("Email field cannot be empty!");
                    return;
                }
//                Validate the Password passed
                if (!TextUtils.isEmpty(password)){
                    if (password.length() < 6){
                        mPassword.setError("Password length must be more than 5 characters!");
                        return;
                    }
                } else {
                    mPassword.setError("Password is required!");
                    return;
                }
//                Show the progress bar
                mProgressBar.setVisibility(View.VISIBLE);
//                Create user in Firebase
                mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            On successful creation, redirect to MainActivity
                            Toast.makeText(getApplicationContext(),
                                    "User created successfully.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else{
                            Toast.makeText(RegisterActivity.this, "Error! "+
                                    task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}