package com.angshumaan.rodalee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Consumerdocuments extends AppCompatActivity {
    //private final static String  KEY_LEASED = "LeasedConsent_letter";
    Button leased, Noc, upload;
    ImageView imageview1, imageView2;
    private String Leased, ImageView1, NOC, Upload, ImageView2, leasedImage , NocImage;
    Bitmap bitmap ,bitmapp;
    private String power_url = "http://192.168.43.219/database/uploadImage.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumerdocuments);
        leased = findViewById(R.id.btn1);
        imageview1 = findViewById(R.id.view1);
        Noc = findViewById(R.id.btn3);
        imageView2 = findViewById(R.id.view2);
        upload = findViewById(R.id.btn4);
        leased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(Consumerdocuments.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();


            }
        });
        Noc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(Consumerdocuments.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 2);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();


            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, power_url
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Consumerdocuments.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Consumerdocuments.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("LeasedConsent_letter", leasedImage);
                        params.put("Noc_letter",NocImage);
                        return params;
                    }
                };

                MySingleton.getInstance(Consumerdocuments.this).addToRequestQueue(request);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageview1.setImageBitmap(bitmap);
                leasedStore(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            try {
                InputStream streammm = getContentResolver().openInputStream(path);
                bitmapp=BitmapFactory.decodeStream(streammm);
                imageView2.setImageBitmap(bitmapp);
                NOCStore(bitmapp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }


    private void leasedStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        leasedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

    private void NOCStore(Bitmap bitmapp) {

        ByteArrayOutputStream streamm = new ByteArrayOutputStream();
        bitmapp.compress(Bitmap.CompressFormat.JPEG, 100, streamm);

        byte[] imageBytes = streamm.toByteArray();

        NocImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
}