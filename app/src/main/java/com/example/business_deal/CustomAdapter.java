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

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Business_class> {

    private Activity context;
    private List<Business_class> business_order;

    public CustomAdapter(Activity context, List<Business_class> business_order) {
        super(context, R.layout.sample, business_order);
        this.context = context;
        this.business_order = business_order;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample,null,true);


        Business_class business_class = business_order.get(position);

        TextView nametext,fhnametext,adresstext,mobiletext,descriptiontext,totalweighttext,moneytext,tokentext,resertext,withawtext;
        nametext = view.findViewById(R.id.nameid);
        fhnametext = view.findViewById(R.id.fhnameid);
        adresstext = view.findViewById(R.id.adressid);;
        mobiletext = view.findViewById(R.id.mobileid);
        descriptiontext = view.findViewById(R.id.descriptionid);
        totalweighttext = view.findViewById(R.id.totalweightid);
        moneytext = view.findViewById(R.id.moneyid);
        tokentext = view.findViewById(R.id.tokenid);
        resertext = view.findViewById(R.id.reserveid);
        withawtext = view.findViewById(R.id.withdrawnid);


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


        return view;
    }
}
