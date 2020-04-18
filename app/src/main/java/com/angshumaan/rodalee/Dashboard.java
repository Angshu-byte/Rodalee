package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Button logoutBtn = findViewById(R.id.logbut);

      logoutBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
               Intent i = new Intent(Dashboard.this, Login.class);
               startActivity(i);
                finish();

          }
   });


        ImageView con = (ImageView) findViewById(R.id.consumer);

        con.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Signupconsumer.class);
               startActivity(i);

            }
        });

        ImageView ven = (ImageView) findViewById(R.id.vendorreg);

        ven.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Signupvendor.class);
                startActivity(i);

            }
        });
    }
}
