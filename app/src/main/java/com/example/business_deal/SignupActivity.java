package com.example.business_deal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity {

    private EditText emailtext,passwordtext,conpasstext;
    private Button registerbutton;
    private String email,password,cpass;
    private ProgressBar progressBar;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.setTitle("Sign up");

        emailtext = findViewById(R.id.emailid);
        passwordtext = findViewById(R.id.passwordid);
        conpasstext = findViewById(R.id.confirmpasswordid);
        registerbutton = findViewById(R.id.registerbtn);
        mauth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailtext.getText().toString().trim();
                password = passwordtext.getText().toString().trim();
                cpass = conpasstext.getText().toString().trim();

                if(email.isEmpty())
                {
                    emailtext.setError("Enter an email adress");
                    emailtext.requestFocus();
                    return;
                }

                 if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    emailtext.setError("Enter a valid email adress");
                    emailtext.requestFocus();
                    return;
                }

                 if(password.length() < 6)
                {
                    passwordtext.setError("Password length must be at least 6");
                    passwordtext.requestFocus();
                    return;
                }

                 if(!password.equals(cpass))
                 {
                     conpasstext.setError("Password doesn't match");
                     conpasstext.requestFocus();
                     return;
                 }

                 progressBar.setVisibility(View.VISIBLE);
                    mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                                startActivity(intent);
                            } else {

                                if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                {
                                    Toast.makeText(getApplicationContext(), "User alrrady registered", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
        });
    }
}