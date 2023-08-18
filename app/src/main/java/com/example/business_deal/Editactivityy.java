package com.example.business_deal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Editactivityy extends AppCompatActivity {

    private Button editbutton;
    private Calendar mycalendar,mycalendar2;
    DatabaseReference databaseReference;
    private String name,fhname,adress,mobileno,description,totalweight,money,tokenno,rdate,wdate,reservedate,withdrawdate,email;
    private EditText reservetext,withdrawtext,nametext,fhnametext,adresstext,mobilenotext,descriptiontext,totalweighttext,moneytext,tokennotext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editactivityy);

        this.setTitle("Edit Record");


        databaseReference = FirebaseDatabase.getInstance().getReference("Record");
        editbutton = findViewById(R.id.editid);

        nametext = findViewById(R.id.nameid);
        fhnametext = findViewById(R.id.fatorhusid);
        adresstext = findViewById(R.id.adressid);
        mobilenotext = findViewById(R.id.phnid);
        descriptiontext = findViewById(R.id.descid);
        totalweighttext = findViewById(R.id.weightid);
        moneytext = findViewById(R.id.moneyid);
        tokennotext = findViewById(R.id.tokenid);
        reservetext = findViewById(R.id.reserveid);
        withdrawtext = findViewById(R.id.withdrawnid);

        mycalendar = Calendar.getInstance();
        mycalendar2 = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener dateset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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

        reservetext.setOnClickListener(view ->{
            new DatePickerDialog(Editactivityy.this,dateset,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DATE)).show();
        });


        withdrawtext.setOnClickListener(view ->{
            new DatePickerDialog(Editactivityy.this,dateset2,mycalendar2.get(Calendar.YEAR),mycalendar2.get(Calendar.MONTH),mycalendar2.get(Calendar.DATE)).show();
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            name = bundle.getString("name").toString().trim();
            fhname = bundle.getString("fhname").toString().trim();
            adress = bundle.getString("adress").toString().trim();
            mobileno = bundle.getString("mobileno").toString().trim();
            description = bundle.getString("description").toString().trim();
            totalweight = bundle.getString("totalweight").toString().trim();
            money = bundle.getString("money").toString().trim();
            tokenno = bundle.getString("tokenno").toString().trim();
            reservedate = bundle.getString("reservedate").toString().trim();
            withdrawdate = bundle.getString("withdrawndate").toString().trim();

            nametext.setText(name);
            fhnametext.setText(fhname);
            adresstext.setText(adress);
            mobilenotext.setText(mobileno);
            descriptiontext.setText(description);
            totalweighttext.setText(totalweight);
            moneytext.setText(money);
            tokennotext.setText(tokenno);
            reservetext.setText(reservedate);
            withdrawtext.setText(withdrawdate);
        }


        editbutton.setOnClickListener(new View.OnClickListener() {
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
                reservedate = reservetext.getText().toString().trim();
                withdrawdate = withdrawtext.getText().toString().trim();

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
                    Toast.makeText(getApplicationContext(),"Record edited successfully",Toast.LENGTH_SHORT).show();

                    nametext.setText("");
                    fhnametext.setText("");
                    adresstext.setText("");
                    mobilenotext.setText("");
                    descriptiontext.setText("");
                    totalweighttext.setText("");
                    moneytext.setText("");
                    tokennotext.setText("");
                    reservetext.setText("");
                    withdrawtext.setText("");


                    Intent intent = new Intent(Editactivityy.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void updatelabel()
    {
        String myformat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        reservetext.setText(dateFormat.format(mycalendar.getTime()));
    }

    public void updatewithdate()
    {
        String myformat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myformat, Locale.US);
        withdrawtext.setText(dateFormat.format(mycalendar2.getTime()));
    }
}