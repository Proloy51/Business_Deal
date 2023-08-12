package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView signuptext,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signuptext = findViewById(R.id.signupid);
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.passwordid);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signuptext.setVisibility(View.GONE);
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