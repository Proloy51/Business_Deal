package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertRecordactivity extends AppCompatActivity {

    private String email;
    private EditText rdatetext,wdatetext,nametext,fhnametext,adresstext,mobilenotext,descriptiontext,totalweighttext,moneytext,tokennotext;
    private Calendar mycalendar,mycalendar2;
    private Button savebutton;
    private DatabaseReference databaseReference;

    private String name,fhname,adress,mobileno,description,totalweight,money,tokenno,rdate,wdate,reservedate,withdrawdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recordactivity);

        this.setTitle("Add record");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            email = bundle.getString("Email").toString().trim();
        }
        nametext = findViewById(R.id.nameid);
        fhnametext = findViewById(R.id.fatorhusid);
        adresstext = findViewById(R.id.adressid);
        mobilenotext = findViewById(R.id.phnid);
        descriptiontext = findViewById(R.id.descid);
        totalweighttext = findViewById(R.id.weightid);
        moneytext = findViewById(R.id.moneyid);
        tokennotext = findViewById(R.id.tokenid);
        rdatetext = findViewById(R.id.dateid);
        wdatetext = findViewById(R.id.withid);

        databaseReference = FirebaseDatabase.getInstance().getReference("Record");

        savebutton = findViewById(R.id.uploadid);

        mycalendar = Calendar.getInstance();
        mycalendar2 = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,int dayOfMonth) {
                mycalendar.set(Calendar.YEAR,year);
                mycalendar.set(Calendar.MONTH,month);
                mycalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                mycalendar2.set(Calendar.YEAR,year);
                mycalendar2.set(Calendar.MONTH,month);
                mycalendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                updatelabel();
            }
        };
        DatePickerDialog.OnDateSetListener dateset2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,int dayOfMonth) {
                mycalendar.set(Calendar.YEAR,year);
                mycalendar.set(Calendar.MONTH,month);
                mycalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                mycalendar2.set(Calendar.YEAR,year);
                mycalendar2.set(Calendar.MONTH,month);
                mycalendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                updatewithdate();
            }
        };

        rdatetext.setOnClickListener(view ->{
            new DatePickerDialog(InsertRecordactivity.this,dateset,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DATE)).show();
        });


        wdatetext.setOnClickListener(view ->{
            new DatePickerDialog(InsertRecordactivity.this,dateset2,mycalendar2.get(Calendar.YEAR),mycalendar2.get(Calendar.MONTH),mycalendar2.get(Calendar.DATE)).show();
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nametext.getText().toString().trim();
                fhname = fhnametext.getText().toString().trim();
                adress = adresstext.getText().toString().trim();
                mobileno = mobilenotext.getText().toString();
                description = descriptiontext.getText().toString().trim();
                totalweight = totalweighttext.getText().toString().trim();
                money = moneytext.getText().toString().trim();
                tokenno = tokennotext.getText().toString().trim();
                reservedate = rdatetext.getText().toString().trim();
                withdrawdate = wdatetext.getText().toString().trim();

                if(name.equals("") || adress.equals("") || mobileno.equals("") || description.equals("") || totalweight.equals("") || money.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Provide all informations properly",Toast.LENGTH_SHORT).show();
                }
                else if(mobileno.length()!=11 || Character.getNumericValue(mobileno.charAt(2))<3)
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid mobile number",Toast.LENGTH_SHORT).show();
                }

                else {

                    Business_class business_class = new Business_class(name,fhname,adress,mobileno,description,totalweight,money,tokenno,reservedate,withdrawdate,email);
                    databaseReference.child(mobileno).setValue(business_class);
                    Toast.makeText(getApplicationContext(),"Record inserted successfully",Toast.LENGTH_SHORT).show();

                    nametext.setText("");
                    fhnametext.setText("");
                    adresstext.setText("");
                    mobilenotext.setText("");
                    descriptiontext.setText("");
                    totalweighttext.setText("");
                    moneytext.setText("");
                    tokennotext.setText("");
                    rdatetext.setText("");
                    wdatetext.setText("");


                    Intent intent = new Intent(InsertRecordactivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void updatelabel()
    {
        String myformat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        rdatetext.setText(dateFormat.format(mycalendar.getTime()));
    }

    public void updatewithdate()
    {
        String myformat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        wdatetext.setText(dateFormat.format(mycalendar2.getTime()));
    }
}