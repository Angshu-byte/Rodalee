package com.angshumaan.rodalee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAPDCLVendor extends Fragment {
    private static final String TAG = AdminAPDCLVendor.class.getSimpleName();

    Button submit;
    private TextInputEditText apdclvendorname ;
    private String APDCLVendorName ;
    private String apdcl_url = "http://192.168.43.219/database/APDCLVendor_details.php";



    public AdminAPDCLVendor() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_apdcl_vendor, container, false);
        apdclvendorname = v.findViewById(R.id.edit1);
        submit = v.findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APDCLVendorName = apdclvendorname.getText().toString().trim();
                makeJsonObjectRequest(v);

            }
        });
        return v;
    }

    private void makeJsonObjectRequest(View v) {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put("APDCLVendorName", APDCLVendorName);
            request.put("Approvedby", "APDCL");
        } catch (
                JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, apdcl_url, request, new Response.Listener<JSONObject>() {
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