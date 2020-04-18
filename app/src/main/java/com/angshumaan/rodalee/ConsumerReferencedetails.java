package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsumerReferencedetails extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_EMPTY = "";
    private static final String KEY_CONSUMER = "Consumer_no";
    private static final String KEY_NAME = "Name";
    private static final String KEY_SUBDIVISION = "Subdivision";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_EMAI = "Email";
    private static final String KEY_MOBILE = "Mobile";
    private static final String KEY_PINCODE = "Pincode";
    private static final String KEY_METERNO = "Meterno";
    private static final String KEY_TEXTAREA = "Address";

    private TextView textView1, textView2, textView3, textView4,  textView6, textView7, textView8, textView9, textView10;
    Spinner subdivision, category;
    Button Save ;
    private EditText consumer, name, pincode, mobileno, email, textArea ,meterno;
    private String Consumerno, Consumername, Consumerdivision, Consumercategory, Consumerestmeter, Consumerpincode, Consumermobile, Consumeremail, Consumeraddress, Consumermeterno;
    private String consumer_url = "http://192.168.43.219/database/consumer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consumer_referencedetails);

        //Text view hooks
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textArea = findViewById(R.id.Address);

        //main hooks
        pincode = findViewById(R.id.Pincode);
        email = findViewById(R.id.Email);
        mobileno = findViewById(R.id.Mobileno);
        name = findViewById(R.id.Name);
        consumer = findViewById(R.id.Consumer);
        subdivision = findViewById(R.id.subdivision);
        category = findViewById(R.id.category);
        meterno = findViewById(R.id.meter);
        Save = findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumerno = consumer.getText().toString().trim();
                Consumername = name.getText().toString().trim();
                Consumerdivision = subdivision.getSelectedItem().toString();
                Consumercategory = category.getSelectedItem().toString();
                Consumermeterno = meterno.getText().toString();
                Consumerpincode = pincode.getText().toString().trim();
                Consumermobile = mobileno.getText().toString().trim();
                Consumeremail = email.getText().toString().trim();
                Consumeraddress = textArea.getText().toString().trim();
                if (validateInputs()) {
                    makeJsonObjectRequest();
                }



            }
        });


        //methods
        populateSubdivision();
        populatecategory();


        //asterik symbol for text view
        String con = "<font color='#000000'>Consumer No </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView1.setText(Html.fromHtml(con));
        String name = "<font color='#000000'>Name </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView2.setText(Html.fromHtml(name));
        String division = "<font color='#000000'>Sub Division </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView3.setText(Html.fromHtml(division));
        String category = "<font color='#000000'>Category </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView4.setText(Html.fromHtml(category));
        String type = "<font color='#000000'> </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView6.setText(Html.fromHtml(type));
        String pincode = "<font color='#000000'>Pincode </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView7.setText(Html.fromHtml(pincode));
        String mobile = "<font color='#000000'>Mobile No </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView8.setText(Html.fromHtml(mobile));
        String email = "<font color='#000000'>Email </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView9.setText(Html.fromHtml(email));
        String address = "<font color='#000000'>Address for Correspondence </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        textView10.setText(Html.fromHtml(address));
    }

    private void populateSubdivision() {
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.sub_divison));
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdivision.setAdapter(divisionAdapter);
    }

    private void populatecategory() {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.category));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(categoryAdapter);
    }

    private void loadNext() {
        Intent i = new Intent(ConsumerReferencedetails.this,Applicantdetails.class);
        startActivity(i);
        finish();

    }
    private void makeJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_CONSUMER, Consumerno);
            request.put(KEY_NAME, Consumername);
            request.put(KEY_SUBDIVISION, Consumerdivision);
            request.put(KEY_CATEGORY, Consumercategory);
            request.put(KEY_METERNO, Consumermeterno);
            request.put(KEY_PINCODE, Consumerpincode);
            request.put(KEY_MOBILE, Consumermobile);
            request.put(KEY_EMAI, Consumeremail);
            request.put(KEY_TEXTAREA, Consumeraddress);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, consumer_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt(KEY_STATUS) == 0) {
                                loadNext();
                            }
                             else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existing
                               consumer.setError("Username already taken!");
                                consumer.requestFocus();


                            }else{
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
        if (KEY_EMPTY.equals(Consumerno)) {
            consumer.setError("Consumer no cannot be empty");
            consumer.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(Consumername)) {
            name.setError("Name cannot be empty");
            name.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Consumermobile)) {
            mobileno.setError("Mobile no cannot be empty");
            mobileno.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(Consumerpincode)) {
            pincode.setError("Pincode cannot be empty");
            pincode.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Consumeremail)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
            return false;
        }
            if (KEY_EMPTY.equals(Consumermeterno)) {
                meterno.setError("Email cannot be empty");
                meterno.requestFocus();
                return false;
        }
        if (KEY_EMPTY.equals(Consumeraddress)) {
           textArea.setError("Address cannot be empty");
            textArea.requestFocus();
            return false;
        }
        return true;
    }
}
