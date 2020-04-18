package com.angshumaan.rodalee;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;


public class VendorApplicantDetails extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_EMPTY = "";
    private static final String KEY_MESSAGE = "message";
    Button save;
    EditText applicantname, registeredaddress, registeredmobile, registeredpin, registeredemail, registeredwebsite, authorizedname, authorizedaddress, authorizeddesignation, authorizedemail, authorizedmobile;
    private String Applicantname, Registeredaddress, Registeredmobile, Registeredpin, Registeredemail, Registeredwebsite, Authorizedname, Authorizedaddress, Authorizeddesignation, Authorizedemail, Authorizedmobile;
    private String app_url = "http://192.168.43.219/database/VendorApplicant_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_applicant_details);
        applicantname = findViewById(R.id.applicantname);
        registeredaddress = findViewById(R.id.registeredaddress);
        registeredmobile = findViewById(R.id.registeredmobile);
        registeredpin = findViewById(R.id.registeredpin);
        registeredemail = findViewById(R.id.registeredemail);
        registeredwebsite = findViewById(R.id.registeredwebsite);
        authorizedname = findViewById(R.id.authorizedname);
        authorizedaddress = findViewById(R.id.authorizedaddress);
        authorizeddesignation = findViewById(R.id.authorizeddesignation);
        authorizedemail = findViewById(R.id.authorizedemail);
        authorizedmobile = findViewById(R.id.authorizedmobile);
        /***Radiobutton hooks **/

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Applicantname = applicantname.getText().toString();
                Registeredaddress = registeredaddress.getText().toString();
                Registeredmobile = registeredmobile.getText().toString();
                Registeredpin = registeredpin.getText().toString();
                Registeredemail = registeredemail.getText().toString();
                Registeredwebsite = registeredwebsite.getText().toString();
                Authorizedname = authorizedname.getText().toString();
                Authorizedaddress = authorizedaddress.getText().toString();
                Authorizeddesignation = authorizeddesignation.getText().toString();
                Authorizedemail = authorizedemail.getText().toString();
                Authorizedmobile = authorizedmobile.getText().toString();
                if(validateInputs()) {
                    makeJsonObjectRequest();
                }
            }
        });


    }

    private void makeJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {

            //Populate the request parameters
            request.put("ApplicantName", Applicantname);
            request.put("RegisteredAddress", Registeredaddress);
            request.put("RegisteredMobile", Registeredmobile);
            request.put("RegisteredPin", Registeredpin);
            request.put("RegisteredEmail", Registeredemail);
            request.put("RegisteredWebsite", Registeredwebsite);
            request.put("AuthorizedName", Authorizedname);
            request.put("AuthorizedAddress", Authorizedaddress);
            request.put("AuthorizedDesignation", Authorizeddesignation);
            request.put("AuthorizedEmail", Authorizedemail);
            request.put("AuthorizedMobile", Authorizedmobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, app_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt(KEY_STATUS) == 0) {
                                //loadNext();
                            } else {
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

    private boolean validateInputs() {
        if (KEY_EMPTY.equals(Applicantname)) {
            applicantname.setError("Cannot be empty");
            applicantname.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Registeredaddress)) {
            registeredaddress.setError(" Cannot be empty");
            registeredaddress.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Registeredmobile)) {
            registeredmobile.setError("Cannot be empty");
            registeredmobile.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Registeredpin)) {
            registeredpin.setError("Cannot be empty");
            registeredpin.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Registeredemail)) {
            registeredemail.setError("Cannot be empty");
            registeredemail.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Registeredwebsite)) {
            registeredwebsite.setError("Cannot be empty");
            registeredwebsite.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Authorizedname)) {
            authorizedname.setError("Cannot be empty");
            authorizedname.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Authorizedaddress)) {
            authorizedaddress.setError("Cannot be empty");
            authorizedaddress.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Authorizeddesignation)) {
            authorizeddesignation.setError("Cannot be empty");
            authorizeddesignation.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Authorizedemail)) {
            authorizedemail.setError("Cannot be empty");
            authorizedemail.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(Authorizedmobile)) {
            authorizedmobile.setError("Cannot be empty");
            authorizedmobile.requestFocus();
            return false;
        }
        return true;
    }
}