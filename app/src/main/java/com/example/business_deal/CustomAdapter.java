package com.example.business_deal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Business_class> {

    private Activity context;
    private List<Business_class> business_order;

    private List<Business_class> filteredItems=new ArrayList<>();



    public CustomAdapter(Activity context, List<Business_class> business_order) {
        super(context, R.layout.sample, business_order);
        this.context = context;
        this.business_order = new ArrayList<>();
        this.business_order.addAll(business_order);
        this.filteredItems = business_order;
        //filteredItems.addAll(business_order);
    }


    public void filter(String query) {
        filteredItems.clear();
        if (query.trim().isEmpty()) {
            filteredItems.addAll(business_order);
        } else {
            query = query.toLowerCase();
            for (Business_class item : business_order) {
                if ((item.getName().toLowerCase().contains(query)) || (item.getDateofreservation().toLowerCase().contains(query)) || (item.getMobno().toLowerCase().contains(query))){
                    filteredItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample,null,true);

        Business_class business_class = filteredItems.get(position);

        TextView emailtext,nametext,fhnametext,adresstext,mobiletext,descriptiontext,totalweighttext,moneytext,tokentext,resertext,withawtext;
        nametext = view.findViewById(R.id.nameid);
        fhnametext = view.findViewById(R.id.fhnameid);
        adresstext = view.findViewById(R.id.adressid);
        mobiletext = view.findViewById(R.id.mobileid);
        descriptiontext = view.findViewById(R.id.descriptionid);
        totalweighttext = view.findViewById(R.id.totalweightid);
        moneytext = view.findViewById(R.id.moneyid);
        tokentext = view.findViewById(R.id.tokenid);
        resertext = view.findViewById(R.id.reserveid);
        withawtext = view.findViewById(R.id.withdrawnid);
       // emailtext = view.findViewById(R.id.emailid);



        nametext.setText(business_class.getName());
        fhnametext.setText(business_class.getFhname());
        adresstext.setText(business_class.getAdress());
        mobiletext.setText(business_class.getMobno());
        descriptiontext.setText(business_class.getDescription());
        totalweighttext.setText(business_class.getTotalweight());
        moneytext.setText(business_class.getMoney());
        tokentext.setText(business_class.getTokenno());
        resertext.setText(business_class.getDateofreservation());
        withawtext.setText(business_class.getDateofwithdrawn());
      //  emailtext.setText(business_class.getEmail());



        String str = business_class.getDateofwithdrawn().toString().trim();

        if(str.length() > 5)
        {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.tomato));
        }
        else{
            view.setBackgroundColor(0);
        }
        return view;
    }
}
