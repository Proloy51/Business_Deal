package com.example.business_deal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageButton addbutton;
    private FirebaseAuth mauth;
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Business_class> business_classList;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addbutton = findViewById(R.id.adddatabtn);
        listView = findViewById(R.id.listviewid);
        mauth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Record");

        business_classList = new ArrayList<>();
        customAdapter = new CustomAdapter(HomeActivity.this,business_classList);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,InsertRecordactivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.signoutid)
        {
            mauth.signOut();
            finish();
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                business_classList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren())
                {
                   Business_class business_class = dataSnapshot1.getValue(Business_class.class);
                   business_classList.add(business_class);
                }

                listView.setAdapter(customAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = String.valueOf(business_classList.get(position).getName());
                        String fhname = String.valueOf(business_classList.get(position).getFhname());
                        String adress = String.valueOf(business_classList.get(position).getAdress());
                        String mobileno = String.valueOf(business_classList.get(position).getMobno());
                        String description = String.valueOf(business_classList.get(position).getDescription());
                        String totalweight = String.valueOf(business_classList.get(position).getTotalweight());
                        String money = String.valueOf(business_classList.get(position).getMoney());
                        String tokenno = String.valueOf(business_classList.get(position).getTokenno());
                        String reservedate = String.valueOf(business_classList.get(position).getDateofreservation());
                        String withdrawndate = String.valueOf(business_classList.get(position).getDateofwithdrawn());


                        Intent intent = new Intent("name",name);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}