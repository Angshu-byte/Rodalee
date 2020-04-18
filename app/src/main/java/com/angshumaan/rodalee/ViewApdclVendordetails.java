package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewApdclVendordetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_apdcl_vendordetails);
        TextView id, name, approved;
        int position;

            //Initializing Views
            id = findViewById(R.id.id);
            name = findViewById(R.id.name);
            approved = findViewById(R.id.approved);

            Intent intent = getIntent();
            position = intent.getExtras().getInt("position");

            id.setText("ID: " + AdminAddVendorDetails.arrayListApdclVendor.get(position).getApdclvendor_id());
            name.setText("Name: " + AdminAddVendorDetails.arrayListApdclVendor.get(position).getAPDCLVendorName());
            approved.setText("Approved by: " + AdminAddVendorDetails.arrayListApdclVendor.get(position).getApprovedby());

        }
    }

