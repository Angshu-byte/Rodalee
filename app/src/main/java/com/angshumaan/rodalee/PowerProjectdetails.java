package com.angshumaan.rodalee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PowerProjectdetails extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_EMPTY = "";
    private static final String KEY_GRAM = "Grampanchayat_etc";
    private static final String KEY_HOUSE = "House_etc";
    private static final String KEY_POWERPLANT = "Groundmounted_kWP";
    private static final String KEY_ESTABLISHMENT = "Establishment";
    private static final String KEY_AREA = "Area";
    private static final String KEY_CAPACITY = "Capacity";
    private static final String KEY_MNRE = "Subsidy_scheme";
    private static final String KEY_OWNER = "Owner";
    private static final String KEY_FEES = "Fees";
    private static final String KEY_LEASEDIMAGE = "LeasedLetter_doc";
    private static final String KEY_NOCIMAGE = "NOCLetter_doc";
    RadioButton Mnreyes, Mnreno, Applicant, Leased, Apartment;
    String[] listItems = {"CAPEX", "RESCO"};
    EditText GramPanchayat, Building, Powerplant, RooftopCapacity, Fees;
    Spinner Establishment, Area;
    TextView Textview1, Textview2, Textview3, Textview4, Textview5, Textview6, Textview7, Textview8, Textview9, Textview10;
    Button Save;
    private String grampanchayat, building, powerplant, fees, establishment, area, capacity, mnre, owner, Selection;
    double x = 0.01;
    private String power_url = "http://192.168.43.219/database/PowerProject_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_projectdetails);
        Textview1 = findViewById(R.id.textview1);
        Textview2 = findViewById(R.id.textview2);
        Textview3 = findViewById(R.id.textview3);
        Textview4 = findViewById(R.id.textview4);
        Textview5 = findViewById(R.id.textview5);
        Textview6 = findViewById(R.id.textview6);
        Textview7 = findViewById(R.id.textview7);
        Textview8 = findViewById(R.id.textview8);
        Textview9 = findViewById(R.id.textview9);
        Textview10 = findViewById(R.id.textview10);

        GramPanchayat = findViewById(R.id.edit1);
        Building = findViewById(R.id.edit2);
        Powerplant = findViewById(R.id.edit3);
        RooftopCapacity = findViewById(R.id.edit4);
        RooftopCapacity.addTextChangedListener(textWatcher);
        Fees = findViewById(R.id.edit5);
        Establishment = findViewById(R.id.establishment);
        Area = findViewById(R.id.roof);
        Applicant = findViewById(R.id.applicant);
        Leased = findViewById(R.id.leased);
        Apartment = findViewById(R.id.apartment);
        Mnreyes = findViewById(R.id.MnreYes);
        Mnreno = findViewById(R.id.MnreNo);
        Save = findViewById(R.id.Powerbutton);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grampanchayat = GramPanchayat.getText().toString();
                building = Building.getText().toString().trim();
                powerplant = Powerplant.getText().toString();
                establishment = Establishment.getSelectedItem().toString();
                area = Area.getSelectedItem().toString();
                capacity = Textview10.getText().toString();
                if (Applicant.isChecked()) {
                    owner = Applicant.getText().toString();
                } else if (Leased.isChecked()) {
                    owner = Leased.getText().toString();
                } else if (Apartment.isChecked()) {
                    owner = Apartment.getText().toString();
                }
                fees = Fees.getText().toString();
                if(validateInputs()) {
                    makeJsonObjectRequest();
                }


            }
        });

        Mnreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PowerProjectdetails.this);
                View mView = getLayoutInflater().inflate(R.layout.customspinner, null);
                mBuilder.setTitle("Select Vendor");
                final Spinner mSpinner = mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PowerProjectdetails.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Custom_Spinner));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Choose")) {
                            //Toast.makeText(PowerProjectdetails.this, mSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            mnre = mSpinner.getSelectedItem().toString();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        Mnreyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        String gram = "<font color='#000000'>Name of Gram Panchayat/Municipality Corporation etc </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview1.setText(Html.fromHtml(gram));
        String House = "<font color='#000000'>House No/Building/Apartment Name </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview2.setText(Html.fromHtml(House));
        String Power = "<font color='#000000'>Proposed capacity of Roof Top/ Ground Mounted Solar Power Plant in kWp </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview3.setText(Html.fromHtml(Power));
        String establishment = "<font color='#000000'>Type of Establishment </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview4.setText(Html.fromHtml(establishment));
        String Roof = "<font color='#000000'>Type of Area/Roof where Solar panel will be installed </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview5.setText(Html.fromHtml(Roof));
        String MNRE = "<font color='#000000'>Whether the applicant applied under MNRE subsidy scheme </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview6.setText(Html.fromHtml(MNRE));
        String Building = "<font color='#000000'>Owner of the Building/ Premises </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview7.setText(Html.fromHtml(Building));
        String Solarpanel = "<font color='#000000'>Shadow free area available for installation of solar panel (Rooftop/Ground Area) </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview8.setText(Html.fromHtml(Solarpanel));
        String Fees = "<font color='#000000'>Processing Fees (Rs 20/-) IPO No./Draft No. / Sub Division Cash Receipt No below </font>" + "<font color='#FF0000'>*</font>" + "<font color='#000000'></font>";
        Textview9.setText(Html.fromHtml(Fees));
        populateEstablishment();
        populateArea();
    }


    /***Spinner dropdown population method ***/

    private void populateEstablishment() {
        ArrayAdapter<String> establishmentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.establishment));
        establishmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Establishment.setAdapter(establishmentAdapter);
    }

    private void populateArea() {
        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Area));
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Area.setAdapter(areaAdapter);
    }

    //Text watcher for edit text assigned in text view
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!RooftopCapacity.getText().toString().equals("")) {
                double z = Integer.valueOf(RooftopCapacity.getText().toString()) * x;
                Textview10.setText(String.valueOf((int) z));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PowerProjectdetails.this);
        builder.setTitle("Mention the mode of subsidy scheme");

        int checkedItem = -1; //this will checked the item when user open the dialog
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(PowerProjectdetails.this, "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
               Selection =  listItems[which];
            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               mnre = Selection;
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

//    private void loadNext() {
//        Intent i = new Intent(PowerProjectdetails.this, PowerProjectdetails.class);
//        startActivity(i);
//        finish();
//    }

    private void makeJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_GRAM, grampanchayat);
            request.put(KEY_HOUSE, building);
            request.put(KEY_POWERPLANT, powerplant);
            request.put(KEY_ESTABLISHMENT, establishment);
            request.put(KEY_AREA, area);
            request.put(KEY_CAPACITY, capacity);
            request.put(KEY_MNRE, mnre);
            request.put(KEY_OWNER, owner);
            request.put(KEY_FEES, fees);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, power_url, request, new Response.Listener<JSONObject>() {
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


    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(grampanchayat)) {
            GramPanchayat.setError("Cannot be empty");
            GramPanchayat.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(building)) {
            Building.setError(" Cannot be empty");
            Building.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(powerplant)) {
            Powerplant.setError("Cannot be empty");
            Powerplant.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(capacity)) {
            RooftopCapacity.setError("Cannot be empty");
            RooftopCapacity.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(fees)) {
            Fees.setError("Cannot be empty");
            Fees.requestFocus();
            return false;
        }
        return true;
    }
}