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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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

public class VendorReferencedocuments extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_EMPTY ="";
    private static final String KEY_MESSAGE ="message";
    ImageView imagev ,imagev1,imagev2 ,imagev3 ,image1 ,image2 ,image3 ,image4;
    Bitmap bitmap ,bitmapp , bitmappp ,bitmapppp,bitmapm,bitmapr ,bitmapentitymem ,bitmapentityreg;
    Button Partnershipupload,Companyupload ,Entityupload;
    RadioButton companyregistrationyes , companyregistrationno ,  partnershipfirmyes , partnershipfirmno , entityyes ,entityno;
    private String Companyregistration ,Partnershipfirm ,Entity, encodedImage,Registrationimage ,Partnershipimage, Auditimage ,Partnershipreg ,Partnershipmemo , Entitymem ,Entityreg;
    private String partnership_url = "http://192.168.43.219/database/Partnershipfirm_documents.php";
    private String company_url = "http://192.168.43.219/database/CompanyRegistration_documents.php";
    private String entity_url = "http://192.168.43.219/database/Entity_documents.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_referencedocuments);

        companyregistrationyes = findViewById(R.id.companyregistrationyes);
        companyregistrationno = findViewById(R.id.companyregistrationno);
        partnershipfirmyes = findViewById(R.id.partnershipfirmyes);
        partnershipfirmno = findViewById(R.id.partnershipfirmno);
        entityyes = findViewById(R.id.entityyes);
        entityno = findViewById(R.id.entityno);

        Companyupload =findViewById(R.id.companyupload);
        Partnershipupload = findViewById(R.id.regupload);
        Entityupload = findViewById(R.id.entitypload);
        Companyupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (companyregistrationyes.isChecked()) {
                    Companyregistration = companyregistrationyes.getText().toString();
                } else if (companyregistrationno.isChecked()) {
                    Companyregistration = companyregistrationno.getText().toString();
                }
                comapnyJsonObjectRequest();

            }
        });
        Partnershipupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (partnershipfirmyes.isChecked()) {
                    Partnershipfirm = partnershipfirmyes.getText().toString();
                } else if (partnershipfirmno.isChecked()) {
                    Partnershipfirm = partnershipfirmno.getText().toString();
                }
                partnershipJsonObjectRequest();

            }
        });
        Entityupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entityyes.isChecked()) {
                    Entity = entityyes.getText().toString();
                } else if (entityno.isChecked()) {
                    Entity = entityno.getText().toString();
                }

                entityJsonObjectRequest();

            }
        });


        companyregistrationyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(VendorReferencedocuments.this);
                final View mView = getLayoutInflater().inflate(R.layout.custom_companyregistration_doc, null);
                mBuilder.setTitle("Reference");
                Button Partnershipupload = mView.findViewById(R.id.PartnershipCertificate);
                Button Auditupload =mView.findViewById(R.id.Audit);
                Button Memorandumupload = mView.findViewById(R.id.Memorandumupload );
                Button Registrationupload =mView.findViewById(R.id.Registrationupload);
                imagev = mView.findViewById(R.id.imageView1);
                imagev1 = mView.findViewById(R.id.imageView2);
                imagev2 =mView.findViewById(R.id.imageView3);
                imagev3 = mView.findViewById(R.id.imageView4);

                /***Memorandum upload**/
                Memorandumupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
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

                Registrationupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
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
                Partnershipupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),3);

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

                Auditupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),4);

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
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }

        });

        partnershipfirmyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(VendorReferencedocuments.this);
                final View mView = getLayoutInflater().inflate(R.layout.partnershipfirm_doc, null);
                mBuilder.setTitle("Reference");
                Button Memorandum = mView.findViewById(R.id.Memorandum);
                Button Registration =mView.findViewById(R.id.Registration);
                image1 = mView.findViewById(R.id.image1);
                image2 = mView.findViewById(R.id.image2);


                /***Memorandum upload**/
                Memorandum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),5);

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

                Registration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),6);

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
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }

        });
        entityyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(VendorReferencedocuments.this);
                final View mView = getLayoutInflater().inflate(R.layout.other_entity_doc, null);
                mBuilder.setTitle("Reference");
                Button entitymemorandum = mView.findViewById(R.id.entitymemorandum);
                Button entityregistration =mView.findViewById(R.id.entityregistration);
                image3 = mView.findViewById(R.id.image3);
                image4 = mView.findViewById(R.id.image4);


                /***Memorandum upload**/
                entitymemorandum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),7);

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

                entityregistration.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dexter.withActivity(VendorReferencedocuments.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),8);

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
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

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
                imagev.setImageBitmap(bitmap);

                imageStore(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            try {
                InputStream streammm = getContentResolver().openInputStream(path);
                bitmapp=BitmapFactory.decodeStream(streammm);
                imagev1.setImageBitmap(bitmapp);
                regStore(bitmapp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 3 && resultCode == RESULT_OK && data != null) {

            Uri pathh = data.getData();

            try {
                InputStream streammmm = getContentResolver().openInputStream(pathh);
                bitmappp=BitmapFactory.decodeStream(streammmm);
                imagev2.setImageBitmap(bitmappp);
                partnershipStore(bitmappp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 4 && resultCode == RESULT_OK && data != null) {

            Uri pathhh = data.getData();

            try {
                InputStream streammmm = getContentResolver().openInputStream(pathhh);
                bitmapppp=BitmapFactory.decodeStream(streammmm);
                imagev3.setImageBitmap(bitmapppp);
                auditStore(bitmappp);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 5 && resultCode == RESULT_OK && data != null) {

            Uri file5 = data.getData();

            try {
                InputStream stream1 = getContentResolver().openInputStream(file5);
                bitmapm=BitmapFactory.decodeStream(stream1);
                image1.setImageBitmap(bitmapm);
                memorandum2store(bitmapm);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 6 && resultCode == RESULT_OK && data != null) {

            Uri file6 = data.getData();

            try {
                InputStream stream2 = getContentResolver().openInputStream(file6);
                bitmapr=BitmapFactory.decodeStream(stream2);
                image2.setImageBitmap(bitmapr);
                reg2store(bitmapr);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 7 && resultCode == RESULT_OK && data != null) {

            Uri file7 = data.getData();

            try {
                InputStream stream3 = getContentResolver().openInputStream(file7);
                bitmapentitymem=BitmapFactory.decodeStream(stream3);
                image3.setImageBitmap(bitmapentitymem);
                entitymem(bitmapentitymem);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else if (requestCode == 8 && resultCode == RESULT_OK && data != null) {

            Uri file8 = data.getData();

            try {
                InputStream stream4 = getContentResolver().openInputStream(file8);
                bitmapentityreg=BitmapFactory.decodeStream(stream4);
                image4.setImageBitmap(bitmapentityreg);
                entityreg(bitmapentityreg);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void entityreg(Bitmap bitmapentityreg) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapentityreg.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Entityreg = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void entitymem(Bitmap bitmapentitymem) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapentitymem.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Entitymem = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void reg2store(Bitmap bitmapr) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapr.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Partnershipreg = Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }
    private void memorandum2store(Bitmap bitmapm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapm.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Partnershipmemo = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void partnershipStore(Bitmap bitmappp) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmappp.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Partnershipimage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
    private void auditStore(Bitmap bitmapppp) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapppp.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Auditimage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
    private void regStore(Bitmap bitmapp) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapp.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        Registrationimage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

    private void entityJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {

            //Populate the request parameters
            request.put("Entity", Entity);
            request.put("EntityMemorandum", Entitymem);
            request.put("EntityRegistration", Entityreg);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, entity_url, request, new Response.Listener<JSONObject>() {
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




    /** Partnership firm Json object request**/

    private void partnershipJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {

            //Populate the request parameters
            request.put("PartnershipFirm", Partnershipfirm);
            request.put("PartnershipFirmMemorandum", Partnershipmemo);
            request.put("PartnershipFirmRegistration", Partnershipreg);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, partnership_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt(KEY_STATUS) == 0) {
                                //loadNext();
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();
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


/** Company registration json object request **/

    private void comapnyJsonObjectRequest() {
        JSONObject request = new JSONObject();
        try {

            //Populate the request parameters
            request.put("CompanyRegistration", Companyregistration);
            request.put("CompanyMemorandumdoc", encodedImage);
            request.put("CompanyRegistrationdoc", Registrationimage);
            request.put("CompanyPartnershipdoc", Partnershipimage);
            request.put("CompanyAuditdoc", Auditimage);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, company_url, request, new Response.Listener<JSONObject>() {
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
