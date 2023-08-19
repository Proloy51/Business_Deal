package com.example.business_deal;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView addbutton;
    private FirebaseAuth mauth;
    private String email;
    private ListView listView;
    DatabaseReference databaseReference;
    private List<Business_class> business_classList;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.setTitle("Home page");


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            email = bundle.getString("Email").toString().trim();
        }


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
                intent.putExtra("Email",email);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.searchviewid);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                customAdapter.filter(newText);
                return false;
            }
        });

     //   getMenuInflater().inflate(R.menu.menu_layout,menu);
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

                   if(business_class == null)
                   {
                       continue;
                   }
                   if (email.equals(business_class.getEmail())) {
                       business_classList.add(business_class);
                   }

                }

                customAdapter = new CustomAdapter(HomeActivity.this,business_classList);
                listView.setAdapter(customAdapter);

                if(listView.getChildCount() == 0)
                {
                   Toast.makeText(getApplicationContext(),"No available records",Toast.LENGTH_SHORT).show();
                }

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


                        Intent intent = new Intent(HomeActivity.this,edit_delete.class);


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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder alertdialogbuilder;

        alertdialogbuilder = new AlertDialog.Builder(HomeActivity.this);

        alertdialogbuilder.setTitle("Alert title");
        alertdialogbuilder.setMessage("Do you want to exit ?");
        alertdialogbuilder.setIcon(R.drawable.question1);

        alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();
    }
}