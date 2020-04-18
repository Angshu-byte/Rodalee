package com.angshumaan.rodalee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminAddVendorDetails extends AppCompatActivity {
    ListView listView1 ,listView2,listView3;
    AdapterAedaVendor adapter3;
    AdapterApdclVendor adapter2;
    AdapterOpenVendor adapter;
    RetrieveAedaVendor aedaVendor;
    RetrieveOpenVendor openVendor;
    RetrieveApdclVendor apdclVendor;
    public static ArrayList<RetrieveAedaVendor> arrayListAedaVendor = new ArrayList<>();
    public static ArrayList<RetrieveOpenVendor> arrayListOpenVendor = new ArrayList<>();
    public static ArrayList<RetrieveApdclVendor> arrayListApdclVendor = new ArrayList<>();
    private String open_url = "http://192.168.43.219/database/OpenVendor_retrieve.php";
    private String apdcl_url = "http://192.168.43.219/database/ApdclVendor_retrieve.php";
    private String aeda_url = "http://192.168.43.219/database/AedaVendor_retrieve.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_vendor_details);
        //Open Vendor
        listView1 = findViewById(R.id.myListView);
        listView2 =findViewById(R.id.apdcl);
        listView3 = findViewById(R.id.aeda);
        adapter = new AdapterOpenVendor(getApplicationContext(),arrayListOpenVendor);
        adapter2 = new AdapterApdclVendor(getApplicationContext(),arrayListApdclVendor);
        adapter3 = new AdapterAedaVendor(getApplicationContext(),arrayListAedaVendor);
        listView1.setAdapter(adapter);
        listView2.setAdapter(adapter2);
        listView3.setAdapter(adapter3);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(arrayListAedaVendor.get(position).getAEDAVendorName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), ViewAedaVendordetails.class)
                                        .putExtra("position",position));

                                break;


                            case 1:

                                deleteaedaData(arrayListAedaVendor.get(position).getAedavendor_id());

                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(arrayListApdclVendor.get(position).getAPDCLVendorName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), ViewApdclVendordetails.class)
                                        .putExtra("position",position));

                                break;


                            case 1:

                                deleteapdclData(arrayListApdclVendor.get(position).getApdclvendor_id());

                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });


        /****Open Vendor **/
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(arrayListOpenVendor.get(position).getOpenVendorName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), ViewOpenVendordetails.class)
                                        .putExtra("position",position));

                                break;


                            case 1:

                                deleteData(arrayListOpenVendor.get(position).getOpenVendor_id());

                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });

        retrieveopenvendordata();
        retrieveapdclvendordata();
        retrieveaedavendordata();
    }

    private void deleteaedaData(final String aedavendor_id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.219/database/AedaVendorDelete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminAddVendorDetails.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminAddVendorDetails.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminAddVendorDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("aedavendor_id", aedavendor_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    private void deleteapdclData(final String apdclvendor_id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.219/database/ApdclVendorDelete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminAddVendorDetails.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminAddVendorDetails.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminAddVendorDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("apdclvendor_id", apdclvendor_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    private void deleteData(final String OpenVendor_id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.219/database/OpenVendorDelete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(AdminAddVendorDetails.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AdminAddVendorDetails.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminAddVendorDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("OpenVendor_id", OpenVendor_id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    private void retrieveaedavendordata() {
        StringRequest request = new StringRequest(Request.Method.POST, aeda_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayListAedaVendor.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("admin_add_aeda_vendor");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String aedavendor_id = object.getString("aedavendor_id");
                                    String AEDAVendorName = object.getString("AEDAVendorName");
                                    String Approvedby = object.getString("Approvedby");


                                    aedaVendor = new RetrieveAedaVendor(aedavendor_id, AEDAVendorName, Approvedby);
                                    arrayListAedaVendor.add(aedaVendor);
                                    adapter3.notifyDataSetChanged();


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }

    /** APDCL Vendor **/
    private void retrieveapdclvendordata() {
        StringRequest request = new StringRequest(Request.Method.POST, apdcl_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayListApdclVendor.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("admin_add_apdcl_vendor");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String apdclvendor_id = object.getString("apdclvendor_id");
                                    String APDCLVendorName = object.getString("APDCLVendorName");
                                    String Approvedby = object.getString("Approvedby");


                                    apdclVendor = new RetrieveApdclVendor(apdclvendor_id, APDCLVendorName, Approvedby);
                                    arrayListApdclVendor.add(apdclVendor);
                                    adapter2.notifyDataSetChanged();


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }

    /** Open Vendor **/
    private void retrieveopenvendordata() {
        StringRequest request = new StringRequest(Request.Method.POST, open_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        arrayListOpenVendor.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("admin_add_open_vendor");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String OpenVendor_id = object.getString("OpenVendor_id");
                                    String OpenVendorName = object.getString("OpenVendorName");
                                    String Approvedby = object.getString("Approvedby");


                                    openVendor = new RetrieveOpenVendor(OpenVendor_id, OpenVendorName, Approvedby);
                                    arrayListOpenVendor.add(openVendor);
                                    adapter.notifyDataSetChanged();


                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);


    }

}
