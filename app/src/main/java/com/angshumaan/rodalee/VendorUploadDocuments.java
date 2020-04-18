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

public class VendorUploadDocuments extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_EMPTY ="";
    private static final String KEY_MESSAGE ="message";
    Bitmap bitmap ,bitmapp;
    Button power , work , save;
    ImageView image1 , image2 ;
    private String Imagepower ,Imagework;
    private String docs = "http://192.168.43.219/database/upload_documents.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_upload_documents);
        power = findViewById(R.id.power);
        work = findViewById(R.id.work);
        image1 =findViewById(R.id.image1);
        image2 =findViewById(R.id.image2);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject request = new JSONObject();
                try {

                    //Populate the request parameters
                    request.put("PowerPurchase", Imagepower);
                    request.put("WorkOrder", Imagework);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                        (Request.Method.POST, docs, request, new Response.Listener<JSONObject>() {
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
                MySingleton.getInstance(VendorUploadDocuments.this).addToRequestQueue(jsArrayRequest);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(VendorUploadDocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        Intent intent  = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

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

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(VendorUploadDocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        Intent intent  = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Image"),2);

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
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image1.setImageBitmap(bitmap);

                imagepower(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            try {
                InputStream streammm = getContentResolver().openInputStream(path);
                bitmapp=BitmapFactory.decodeStream(streammm);
                image2.setImageBitmap(bitmapp);
                imagework(bitmapp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    private void imagework(Bitmap bitmapp) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapp.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Imagework = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
    private void imagepower(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Imagepower = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

}
