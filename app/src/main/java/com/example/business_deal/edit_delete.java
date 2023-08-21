package com.example.business_deal;

import static com.example.business_deal.R.id.reserveid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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



    private static final int UNDO_TIMEOUT_MS = 5000; // 5 seconds

    private Handler undoHandler = new Handler();
    private Runnable undoRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(edit_delete.this,HomeActivity.class);
            startActivity(intent);
        }
    };


    private String name,fhname,adress,mobileno,description,totalweight,money,tokenno,reservedate,withdrawndate,email;

    private String name2,fhname2,adress2,mobileno2,description2,totalweight2,money2,tokenno2,reservedate2,withdrawndate2,email2;
    private TextView nametext,fhnametext,adresstext,mobilenotext,descriptiontext,totalweighttext,moneytext,tokennotext,reservetext,withdrawntext;
    private ImageView edit,delete;
    private Button undobutton;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

        this.setTitle("Edit or Delete");


        undobutton = findViewById(R.id.undobutton);

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

            email = bundle.get("Email").toString().trim();
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


            name2 = name;
            adress2 = adress;
            fhname2 = fhname;
            mobileno2 = mobileno;
            description2 = description;
            totalweight2 = totalweight;
            tokenno2 = tokenno;
            money2 = money;
            email2 = email;
            reservedate2 = reservedate;
            withdrawndate2 = withdrawndate;


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

                    intent.putExtra("Email",email);
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

                showundobutton();

                undobutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Business_class business_class = new Business_class(name2,fhname2,adress2,mobileno2,description2,totalweight2,money2,tokenno2,reservedate2,withdrawndate2,email2);
                        databaseReference.child(mobileno).setValue(business_class);
                        databaseReference.child(mobileno).child("email").setValue(email);
                        undoHandler.postDelayed(undoRunnable, UNDO_TIMEOUT_MS);

                        hideundobutton();
                    }
                });

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

    public void showundobutton()
    {
        Button undoButton = findViewById(R.id.undobutton);
        undoButton.setVisibility(View.GONE);
        undoHandler.removeCallbacks(undoRunnable);
    }

    public void hideundobutton()
    {
        Button undoButton = findViewById(R.id.undobutton);
        undoButton.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        undoHandler.removeCallbacks(undoRunnable);
    }
}