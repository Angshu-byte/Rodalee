package com.angshumaan.rodalee;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;


import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminOpenVendor extends Fragment {
    private static final String TAG = AdminOpenVendor.class.getSimpleName();
    Button Submit;
    private TextInputEditText Openvendorname ;
    private String OpenVendorName ;
    private String open_url = "http://192.168.43.219/database/OpenVendor_details.php";


    public AdminOpenVendor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_openvendor, container, false);
        Openvendorname = v.findViewById(R.id.edit1);
        Submit = v.findViewById(R.id.Submit1);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenVendorName = Openvendorname.getText().toString().trim();
                Openvendorname.setText("");
                makeJsonObjectRequest(v);

            }
        });
        return v;
    }



    private void makeJsonObjectRequest(View v) {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("OpenVendorName", OpenVendorName);
            request.put("Approvedby", "Open Vendor");
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, open_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // pDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // pDialog.dismiss();

                        //Display error message whenever an error occurs

                        Toast.makeText(getContext(),
                                "Registration Unsuccessfull", Toast.LENGTH_SHORT).show();

                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(v.getContext()).addToRequestQueue(jsArrayRequest);

    }
}