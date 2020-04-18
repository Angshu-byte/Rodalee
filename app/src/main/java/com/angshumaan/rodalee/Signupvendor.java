package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Signupvendor extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
   private static final String KEY_EMPTY = "";
    private EditText etuser;
    private EditText etpass;
    private EditText etcpass;
    private EditText etname;
    private EditText etemail;
    private EditText etphone;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;
    private String phone;
    Button login , register;
    private ProgressDialog pDialog;
   private String register_url = "http://192.168.43.219/database/vendor_register.php";
   private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_signupvendor);

        etuser = findViewById(R.id.etuser);
        etpass = findViewById(R.id.etpass);
        etcpass = findViewById(R.id.etcpass);
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);

        Button login = findViewById(R.id.ven_login);
        Button register = findViewById(R.id.ven_signup);

        //Launch Login screen when Login Button is clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signupvendor.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                username = etuser.getText().toString().toLowerCase().trim();
                password = etpass.getText().toString().trim();
                confirmPassword = etcpass.getText().toString().trim();
                fullName = etname.getText().toString().trim();
                email = etemail.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                if (validateInputs()) {
                    registerUser();
                }

            }
        });

    }

    /**
     * Display Progress bar while registering
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(Signupvendor.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    /**
     * Launch Dashboard Activity on Successful Sign Up
     */
    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(i);
        finish();

    }

    private void registerUser() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
            request.put(KEY_FULL_NAME, fullName);
            request.put(KEY_EMAIL, email);
            request.put(KEY_PHONE, phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                session.loginUser(username,fullName);
                                loadDashboard();

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                etuser.setError("Username already taken!");
                                etuser.requestFocus();

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
                        pDialog.dismiss();

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
        if (KEY_EMPTY.equals(fullName)) {
            etname.setError("Full Name cannot be empty");
            etname.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(username)) {
            etuser.setError("Username cannot be empty");
            etuser.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            etpass.setError("Password cannot be empty");
            etpass.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(confirmPassword)) {
            etcpass.setError("Confirm Password cannot be empty");
            etcpass.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etcpass.setError("Password and Confirm Password does not match");
            etcpass.requestFocus();
            return false;
        }

        return true;
    }
}
