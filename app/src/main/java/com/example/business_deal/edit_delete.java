package com.example.business_deal;

import static com.example.business_deal.R.id.reserveid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit_delete extends AppCompatActivity {


    private String name,fhname,adress,mobileno,description,totalweight,money,tokenno,reservedate,withdrawndate;
    private TextView nametext,fhnametext,adresstext,mobilenotext,descriptiontext,totalweighttext,moneytext,tokennotext,reservetext,withdrawntext;
    private ImageView edit,delete;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);


        nametext = findViewById(R.id.nameid);
        fhnametext = findViewById(R.id.fhnameid);
        adresstext = findViewById(R.id.adressid);
        mobilenotext = findViewById(R.id.mobileid);
        descriptiontext = findViewById(R.id.descriptionid);
        totalweighttext = findViewById(R.id.totalweightid);
        moneytext = findViewById(R.id.moneyid);
        tokennotext = findViewById(R.id.tokenid);
        reservetext = findViewById(R.id.reserveid);
        withdrawntext = findViewById(R.id.withdrawnid);

        edit = findViewById(R.id.editbutton);
        delete = findViewById(R.id.deletebutton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            name = bundle.getString("name").toString().trim();
            fhname = bundle.getString("fhname").toString().trim();
            adress = bundle.getString("adress").toString().trim();
            mobileno = bundle.getString("mobileno").toString().trim();
            description = bundle.getString("description").toString().trim();
            totalweight = bundle.getString("totalweight").toString().trim();
            money = bundle.getString("money").toString().trim();
            tokenno = bundle.getString("tokenno").toString().trim();
            reservedate = bundle.getString("reservedate").toString().trim();
            withdrawndate = bundle.getString("withdrawndate").toString().trim();

        }

            nametext.setText(name);
            fhnametext.setText(fhname);
            adresstext.setText(adress);
            mobilenotext.setText(mobileno);
            descriptiontext.setText(description);
            totalweighttext.setText(totalweight);
            moneytext.setText(money);
            tokennotext.setText(tokenno);
            reservetext.setText(reservedate);
            withdrawntext.setText(withdrawndate);


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(edit_delete.this,Editactivityy.class);
                    intent.putExtra("name",name);
                    intent.putExtra("fhname",fhname);
                    intent.putExtra("adress",adress);
                    intent.putExtra("mobileno",mobileno);
                    intent.putExtra("description",description);
                    intent.putExtra("totalweight",totalweight);
                    intent.putExtra("money",money);
                    intent.putExtra("tokenno",tokenno);
                    intent.putExtra("reservedate",reservedate);
                    intent.putExtra("withdrawndate",withdrawndate);

                    startActivity(intent);
                }
            });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference("Record");
                databaseReference.child(mobileno)
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(edit_delete.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(edit_delete.this, "Try again", Toast.LENGTH_SHORT).show();
                            }
                        });

                Intent intent = new Intent(edit_delete.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}