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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertRecordactivity extends AppCompatActivity {

    private EditText rdatetext,wdatetext,nametext,fhnametext,adresstext,mobilenotext,descriptiontext,totalweighttext,moneytext,tokennotext;
    private Calendar mycalendar,mycalendar2;
    private Button savebutton;

    private String name,fhname,adress,mobileno,description,totalweight,money,tokenno,rdate,wdate,reservedate,withdrawdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recordactivity);

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

//        reservedate = rdate.substring(1,(rdate.charAt(' ')+1));
//        withdrawdate = wdate.substring(1,(wdate.charAt(' ')+1));


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


                String reservationdate = reservedate.substring(0,(reservedate.charAt(' ')));
                String withdrawndate = withdrawdate.substring(0,(withdrawdate.charAt(' ')));

                Log.d("InsertRecordactivity",reservationdate);
                Log.d("InsertRecordactivity",withdrawndate);

                if(name.equals("") || fhname.equals("") || adress.equals("") || mobileno.equals("") || description.equals("") || totalweight.equals("") || money.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Provide all informations properly",Toast.LENGTH_SHORT).show();
                }
                else if(mobileno.length()!=11 || Character.getNumericValue(mobileno.charAt(2))<3)
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid mobile number",Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent(InsertRecordactivity.this,test.class);
                    intent.putExtra("c",reservationdate);
                    startActivity(intent);
                }
            }
        });

    }

    public void updatelabel()
    {
        String myformat = "MM/dd/yyyy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        rdatetext.setText(dateFormat.format(mycalendar.getTime()));
    }

    public void updatewithdate()
    {
        String myformat = "MM/dd/yyyy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        wdatetext.setText(dateFormat.format(mycalendar2.getTime()));
    }
}