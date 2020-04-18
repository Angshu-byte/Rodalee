package com.angshumaan.rodalee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsumerSubsidyContents extends AppCompatActivity {
TextView Text1 ,Text2;
RadioButton rd1 , rd2 ,rd3 ,rd4 ,rd5;
Button  button;
private String  APDCL , AEDA ,SECI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_subsidy_contents);

        Text1 = findViewById(R.id.Text1);
        Text2 = findViewById(R.id.Text2);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        rd5 = findViewById(R.id.rd5);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ConsumerSubsidyContents.this);
                View mView = getLayoutInflater().inflate(R.layout.customspinner, null);
                mBuilder.setTitle("Select Vendor");
                final Spinner mSpinner = mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ConsumerSubsidyContents.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Custom_Spinner));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Choose")) {
                            //Toast.makeText(PowerProjectdetails.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            APDCL = mSpinner.getSelectedItem().toString();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }
}
