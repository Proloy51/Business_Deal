package com.example.business_deal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView signuptext,emailtext,passwordtext;
    private String email,password;
    private Button loginbutton;
    private ProgressBar progressBar;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Login or Sign up");

        signuptext = findViewById(R.id.signupid);
        emailtext = findViewById(R.id.emailid);
        passwordtext = findViewById(R.id.passwordid);
        loginbutton = findViewById(R.id.loginbtn);

        mauth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar3);




        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailtext.getText().toString().trim();
                password = passwordtext.getText().toString().trim();


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

                progressBar.setVisibility(View.VISIBLE);

                mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect email or password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}