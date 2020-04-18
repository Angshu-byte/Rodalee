package com.angshumaan.rodalee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class VendorFinancial_details extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_EMPTY = "";
    private static final String KEY_MESSAGE = "message";
    EditText tinno , cinno , gstno , validity1 ,validity2 ,validity3, name , address ,mobileno ,email , registrationno , bankname ,bankaddress , bankaccount;
    TextView datetin ,datecin ,dategst , datecharter;
    private DatePickerDialog.OnDateSetListener mDateSetListener ,nDateSetListener,oDateSetListener ,pDateSetListener;
    private String Datetin ,Datecin ,Dategst,Datecharter;
    private String Tinno , Cinno , Gstno , Validity1 ,Validity2 ,Validity3, Name , Address ,Mobileno ,Email , Registrationno , Bankname ,Bankaddress , Bankaccount ,DateTin ,DateCin , DateGst ,DateCharter;
    Button button;
    private String fin_url = "http://192.168.43.219/database/VendorFinancial_details.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_financial_details);
        tinno = findViewById(R.id.edittext1);
        validity1 =findViewById(R.id.edittext2);
        cinno =findViewById(R.id.edittext3);
        validity2 =findViewById(R.id.edittext4);
        gstno =findViewById(R.id.edittext5);
        validity3 =findViewById(R.id.edittext6);
        name =findViewById(R.id.edittext7);
        address =findViewById(R.id.edittext8);
        mobileno =findViewById(R.id.edittext9);
        email =findViewById(R.id.edittext10);
        registrationno = findViewById(R.id.edittext11);
        bankname = findViewById(R.id.edittext12);
        bankaddress = findViewById(R.id.edittext13);
        bankaccount = findViewById(R.id.edittext14);

        datetin = findViewById(R.id.date1);
        datecin= findViewById(R.id.date2);
        dategst = findViewById(R.id.date3);
        datecharter = findViewById(R.id.date4);
        button = findViewById(R.id.but);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinno = tinno.getText().toString();
                Validity1 =validity1.getText().toString();
                Cinno = cinno.getText().toString();
                Validity2 = validity2.getText().toString();
                Gstno = gstno.getText().toString();
                Validity3 = validity3.getText().toString();
                Name = name.getText().toString();
                Address = address.getText().toString();
                Mobileno = mobileno.getText().toString();
                Email = email.getText().toString();
                Registrationno = registrationno.getText().toString();
                Bankname = bankname.getText().toString();
                Bankaddress = bankaddress.getText().toString();
                Bankaccount = bankaccount.getText().toString();
                makeJsonObjectRequest();
            }
        });

        datetin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VendorFinancial_details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Datetin = year+ "-" + month + "-" + day;
                datetin.setText(day+ "-" + month + "-" + year);
            }
        };

        datecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VendorFinancial_details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        nDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        nDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Datecin = year+ "-" + month + "-" + day;
                datecin.setText(day+ "-" + month + "-" + year);
            }
        };


        dategst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VendorFinancial_details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        oDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        oDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Dategst = year+ "-" + month + "-" + day;
                dategst.setText(day+ "-" + month + "-" + year);
            }
        };


        datecharter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        VendorFinancial_details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        pDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        pDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Datecharter = year+ "-" + month + "-" + day;
                datecharter.setText(day+ "-" + month + "-" + year);
            }
        };





    }

    private void makeJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {

            //Populate the request parameters
            request.put("TIN_No", Tinno);
            request.put("TIN_Date", Datetin);
            request.put("TIN_Validity", Validity1);
            request.put("CIN_No", Cinno);
            request.put("CIN_Date", Datecin);
            request.put("CIN_Validity", Validity2);
            request.put("GST_No", Gstno);
            request.put("GST_Date", Dategst);
            request.put("GST_Validity", Validity3);
            request.put("CharterName", Name);
            request.put("CharterAddress", Address);
            request.put("CharterMobile", Mobileno);
            request.put("CharterEmail", Email);
            request.put("CharterRegistration", Registrationno);
            request.put("CharterDate", Datecharter);
            request.put("BankName", Bankname);
            request.put("BankAddress", Bankaddress);
            request.put("AccountNo", Bankaccount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, fin_url, request, new Response.Listener<JSONObject>() {
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
}
