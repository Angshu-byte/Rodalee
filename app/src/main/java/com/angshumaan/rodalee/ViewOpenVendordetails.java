package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewOpenVendordetails extends AppCompatActivity {
    TextView tvid,tvname,tvapproved;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_open_vendordetails);

        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvapproved = findViewById(R.id.txtapproved);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+AdminAddVendorDetails.arrayListOpenVendor.get(position).getOpenVendor_id());
        tvname.setText("Name: "+AdminAddVendorDetails.arrayListOpenVendor.get(position).getOpenVendorName());
        tvapproved.setText("Email: "+AdminAddVendorDetails.arrayListOpenVendor.get(position).getApprovedby());


    }
}
