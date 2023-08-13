package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView signuptext,email,password;
    private String emailstring,pass;
    private Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signuptext = findViewById(R.id.signupid);
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.passwordid);
        loginbutton = findViewById(R.id.loginbtn);


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signuptext.setVisibility(View.GONE);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailstring = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                if(emailstring.equals("proloy@gmail.com") && pass.equals("123456"))
                {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect email or password",Toast.LENGTH_SHORT).show();
                }
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