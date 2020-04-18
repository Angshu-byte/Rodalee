package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewAedaVendordetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aeda_vendordetails);
        TextView id, name, approved;
        int position;

        //Initializing Views
        id = findViewById(R.id.aid);
        name = findViewById(R.id.aname);
        approved = findViewById(R.id.aapproved);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        id.setText("ID: " + AdminAddVendorDetails.arrayListAedaVendor.get(position).getAedavendor_id());
        name.setText("Name: " + AdminAddVendorDetails.arrayListAedaVendor.get(position).getAEDAVendorName());
        approved.setText("Approved by: " + AdminAddVendorDetails.arrayListAedaVendor.get(position).getApprovedby());

    }
}

