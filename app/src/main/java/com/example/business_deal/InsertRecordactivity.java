package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertRecordactivity extends AppCompatActivity {

    EditText rdate,wdate;
    Calendar mycalendar,mycalendar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_recordactivity);

        rdate = findViewById(R.id.dateid);
        wdate = findViewById(R.id.withid);
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
                updatewithdate();
            }
        };

        rdate.setOnClickListener(view ->{
            new DatePickerDialog(InsertRecordactivity.this,dateset,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DATE)).show();
        });


        wdate.setOnClickListener(view ->{
            new DatePickerDialog(InsertRecordactivity.this,dateset,mycalendar2.get(Calendar.YEAR),mycalendar2.get(Calendar.MONTH),mycalendar2.get(Calendar.DATE)).show();
        });

    }

    public void updatelabel()
    {
        String myformat = "MM/dd/yyyy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        rdate.setText(dateFormat.format(mycalendar.getTime()));
    }

    public void updatewithdate()
    {
        String myformat = "MM/dd/yyyy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        wdate.setText(dateFormat.format(mycalendar2.getTime()));
    }
}