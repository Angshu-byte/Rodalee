package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class AdminApplication extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_application);

        toolbar=findViewById(R.id.myToolbar);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.ViewPager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("Vendor details");
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new AdminOpenVendor(),"Open Vendor");
        viewPagerAdapter.addFragment(new AdminAPDCLVendor(),"APDCL Vendor");
        viewPagerAdapter.addFragment(new AdminAEDAVendor(),"AEDA Vendor");
        viewPager.setAdapter(viewPagerAdapter);
    }

}







