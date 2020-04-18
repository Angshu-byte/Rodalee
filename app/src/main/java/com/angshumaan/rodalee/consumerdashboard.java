package com.angshumaan.rodalee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

public class consumerdashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SessionHandler session;
    DrawerLayout drawerlayout;
    NavigationView navigationview;
    Toolbar toolbar;
    private  ImageView myImag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumerdashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView welcomeText = findViewById(R.id.welcomeText);

        welcomeText.setText("Welcome "+user.getFullName()+", your session will expire on "+user.getSessionExpiryDate());

        ImageView myImage = (ImageView) findViewById(R.id.application);

        myImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(consumerdashboard.this,ConsumerReferencedetails.class);
                startActivity(i);

            }
        });



        drawerlayout = findViewById(R.id.drawer_layout);
        navigationview = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);

        Menu menu =navigationview.getMenu();
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationview.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationview.setNavigationItemSelectedListener(this);
        navigationview.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {

        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                break;

            case R.id.status:
              //  Intent intent = new Intent(consumerdashboard.this,Status.class);
              //  startActivity(intent);

            case R.id.nav_logout:
                //session.logoutUser();
                Intent i = new Intent(consumerdashboard.this, Dashboard.class);
                startActivity(i);
                finish();

        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;

    }


}
