package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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


        for(int i=0; i<rdate.length(); i++)
        {
            if(rdate.charAt(i) ==' ')
            {
                break;
            }
            else{
                reservedate += rdate.charAt(i);
            }
        }


        for(int i=0; i<wdate.length(); i++)
        {
            if(wdate.charAt(i) ==' ')
            {
                break;
            }
            else{
                withdrawdate += wdate.charAt(i);
            }
        }

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

                if(name.length()<1 || fhname.length()<1 || adress.length()<1 || mobileno.length()<1 || description.length()<1 || totalweight.length()<1 || money.length()<1 || tokenno.length()<1)
                {
                    Toast.makeText(getApplicationContext(),"Provide all informations properly",Toast.LENGTH_SHORT).show();
                }
                if(mobileno.length()!=11 || mobileno.charAt(2)<3)
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid mobile number",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(InsertRecordactivity.this,HomeActivity.class);
                startActivity(intent);
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