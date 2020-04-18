package com.angshumaan.rodalee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Applicantdetails extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_EMPTY = "";
    private static final String KEY_USABILITY= "Sector_of_usability";
    private static final String KEY_SUPPLY = "Voltage_of_supply";
    private static final String KEY_METER = "Existing_metering_device";
    private static final String KEY_EXISTING = "Existing_supply";
    private static final String KEY_METERING = "Type_of_metering";
    private static final String KEY_DUES = "Dues";
    private static final String KEY_KWP = "kWP";
    private static final String KEY_KVA = "kVA";
    private TextView view1, view2, view3, view4,  view5, view6, view7,view8 , view9 ,view10;
    Spinner usability ,supply ,meteringdevice;
    private RadioButton Single , Three , Prepaid ,Postpaid , Yes , No;
    private EditText connectedLoad , contractdemand;
    private String ofusability, ofsupply ,ofmeter, existing , metering , yes ,oftextview9 , oftextview10;
    private Button submit;
    double x = 0.8;
    double i = 0.85;
    private String applicant_url= "http://192.168.43.219/database/applicant_details.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicantdetails);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);
        view6 = findViewById(R.id.view6);
        view7 = findViewById(R.id.view7);
        view8 = findViewById(R.id.view8);
        view9 = findViewById(R.id.view9);
        view10 = findViewById(R.id.view10);

        //Radiobutton and edit text
        usability = findViewById(R.id.Usability);
        supply = findViewById(R.id.supply);
        meteringdevice = findViewById(R.id.meter);
        Single = findViewById(R.id.single);
        Three = findViewById(R.id.three);
        Prepaid = findViewById(R.id.prepaid);
        Postpaid = findViewById(R.id.postpaid);
        Yes = findViewById(R.id.yes);
        No = findViewById(R.id.no);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Applicantdetails.this);
                builder.setTitle("Alert");
                builder.setMessage("Please clear all your dues before you proceed!!!");
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                               // Toast.makeText(Applicantdetails.this,"Yes is clicked",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
            }
        });
        connectedLoad = findViewById(R.id.connected);
        connectedLoad.addTextChangedListener(textWatcher);
        contractdemand = findViewById(R.id.contract);
        contractdemand.addTextChangedListener(watcher);

        submit =findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofusability = usability.getSelectedItem().toString();
                ofsupply = supply.getSelectedItem().toString();
                ofmeter = meteringdevice.getSelectedItem().toString();
                if (Single.isChecked()) {
                    existing= Single.getText().toString();
                }else if(Three.isChecked()) {
                     existing = Three.getText().toString();
                }
                if (Prepaid.isChecked()) {
                    metering= Prepaid.getText().toString();
                }else if(Postpaid.isChecked()) {
                    metering = Postpaid.getText().toString();
                }
                if (Yes.isChecked()) {
                    yes= Yes.getText().toString();
                }else if(No.isChecked()) {
                     yes = No.getText().toString();
                }
                 oftextview9 = view9.getText().toString().trim();
                 oftextview10= view10.getText().toString().trim();
                if(validateInputs()) {
                    makeJsonObjectRequest();
                }

            }
        });
        populateUsability();
        populateSupply();
        populateMeter();





        String usability = "<font color='#000000'>Sector of Usability </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view1.setText(Html.fromHtml(usability));
        String voltage = "<font color='#000000'>Voltage of Supply </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view2.setText(Html.fromHtml(voltage));
        String metering = "<font color='#000000'>Existing metering device </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view3.setText(Html.fromHtml(metering));
        String supply = "<font color='#000000'>Existing Supply </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view4.setText(Html.fromHtml(supply));
        String meter = "<font color='#000000'>Type of Metering </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view5.setText(Html.fromHtml(meter));
        String has = "<font color='#000000'>Has the consumer any arrear dues to be cleared? </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view6.setText(Html.fromHtml(has));
        String load = "<font color='#000000'>Connected Load(kW) </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view7.setText(Html.fromHtml(load));
        String contract = "<font color='#000000'>Contract demand(For HT Consumer in kVA </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        view8.setText(Html.fromHtml(contract));
    }

    private void populateUsability() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.usability));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usability.setAdapter(categoryAdapter);
    }

    private void populateSupply() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Voltage_of_supply));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supply.setAdapter(categoryAdapter);
    }
    private void populateMeter() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Metering_device));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meteringdevice.setAdapter(categoryAdapter);
    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ( !connectedLoad.getText().toString().equals("")) {
                double z = Integer.valueOf(connectedLoad.getText().toString()) * x;
                view9.setText(String.valueOf((int)z));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ( !contractdemand.getText().toString().equals("")) {
                double j = Integer.valueOf(contractdemand.getText().toString()) * x * i;
                view10.setText(String.valueOf((int)j));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void loadNext() {
        Intent i = new Intent(Applicantdetails.this,PowerProjectdetails .class);
        startActivity(i);
        finish();
    }
    private void makeJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USABILITY,ofusability );
            request.put(KEY_SUPPLY, ofsupply);
            request.put(KEY_METER, ofmeter);
            request.put(KEY_EXISTING, existing);
            request.put(KEY_METERING, metering);
            request.put(KEY_DUES, yes);
            request.put(KEY_KWP, oftextview9);
            request.put(KEY_KVA, oftextview10);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, applicant_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt(KEY_STATUS) == 0) {
                                loadNext();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(yes)) {
            Yes.setError("Please clear all the dues before you proceed!!!");
            Yes.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(oftextview9)) {
            connectedLoad.setError("Connection load cannot be empty");
            connectedLoad.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(oftextview10)) {
            contractdemand.setError("Contract demand cannot be empty");
            contractdemand.requestFocus();
            return false;
        }
        return true;
    }

}
