package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.partyplanets.Activity.GetLocation;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener
{
    private EditText fname,lname,address1,address2,city,state,contact;
    CircleImageView profile;
    Button update;
    String value="";
    Loader loader;
    Data securelogincheckResponse;
    String location="";
    GetLocation getLocation;
    Context context;
    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    String userCountry, userAddress;
    ImageView backprecc,imgupdate;
    int SELECT_PICTURE = 200;
    String imgProfile="";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        context=UpdateProfile.this;
        editprofile();
    }


    public void editprofile()
    {   imgupdate=findViewById(R.id.imgupdate);
        backprecc=findViewById(R.id.backprecc);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        address1=findViewById(R.id.address1);
        address2=findViewById(R.id.address2);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        contact=findViewById(R.id.contact);
        profile=findViewById(R.id.profile);
        update=findViewById(R.id.update);




        imgupdate.setOnClickListener(this);
        update.setOnClickListener(this);
        backprecc.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        value=getIntent().getStringExtra("secureloginResponse");
         securelogincheckResponse = new Gson().fromJson(value, Data.class);
        String Fname = securelogincheckResponse.getFirstName();
        fname.setText(securelogincheckResponse.getFirstName());
        lname.setText(securelogincheckResponse.getLastName());
        address1.setText(securelogincheckResponse.getAddress1());
        address2.setText(securelogincheckResponse.getAddress2());
        city.setText(securelogincheckResponse.getCity());
        state.setText(securelogincheckResponse.getState());
        contact.setText(securelogincheckResponse.getPhoneNo());

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            return ;}
        try {
            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else if (network_loc != null) {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else {
            latitude = 0.0;
            longitude = 0.0;
        }
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0)
            {   userCountry = addresses.get(0).getCountryName();
                userAddress = addresses.get(0).getAddressLine(0);
                // msg.setText(userCountry + ", " + userAddress);
            }
            else
            {   userCountry = "Unknown";
                //msg.setText(userCountry);
            }}
        catch (Exception e) {
            e.printStackTrace();}
        //cuurentlocation.setText(userAddress);

    }
    public void profileUpdate()
    {
      if (fname.getText().toString().isEmpty()) {
            fname.setError("Please enter first Name");
            fname.requestFocus();
        }
        else if (lname.getText().toString().isEmpty())
        {   lname.setError("Please enter last Name");
            lname.requestFocus();
        }
      else if (address1.getText().toString().isEmpty())
      {   address1.setError("Please enter Address");
          address1.requestFocus();
      }
      else if (address2.getText().toString().isEmpty())
      {   address2.setError("Please enter Address");
          address2.requestFocus();
      }
      else if (city.getText().toString().isEmpty())
      {   city.setError("Please enter City Name");
          city.requestFocus();
      }
      else if (state.getText().toString().isEmpty())
      {   state.setError("Please enter state");
          state.requestFocus();
      }else if (contact.getText().toString().isEmpty())
      {   contact.setError("Please Enter Phone Number");
          contact.requestFocus();
      }
      else if(contact.getText().toString().length()!=10)
      {
          contact.setError("Please Enter Minimum  10 Digits ");
          contact.requestFocus();
      }

      else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
        {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {

         /*   loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);*/
            Log.d("fkdjvnbvhkjvjc",securelogincheckResponse.getId());
            UtilsMethod.INSTANCE.UpdateProfile(UpdateProfile.this,loader,securelogincheckResponse.getId().toString(),fname.getText().toString(),lname.getText().toString()
                    ,contact.getText().toString(),city.getText().toString()
                    ,state.getText().toString(),address1.getText().toString()
                    ,address2.getText().toString(),"123456", String.valueOf(latitude), String.valueOf(longitude),"",imgProfile);
        }

    }

    @Override
    public void onClick(View v)
    {
        if(v==update)
        {
            profileUpdate();
        }
        if (v==backprecc)
        {
            onBackPressed();

        }
        if (v==imgupdate)
        {
            verifyStoragePermissions();
            chooseImage();
        }
    }
    public void verifyStoragePermissions() {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(UpdateProfile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void chooseImage()
    {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage, 2);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2)
            {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri)
                {
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = this.getContentResolver().query(selectedImageUri, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    c.close();
                    imgProfile = picturePath;
                    Log.d("imagvjhbge",imgProfile);
                    profile.setImageURI(selectedImageUri);
                }

               /* if (null != selectedImageUri)
                {
                   *//* Uri selectedImage = data.getData();*//*
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImageUri, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    imgProfile=picturePath;
                    Log.d("img",imgProfile);
                    profile.setImageURI(selectedImageUri);
                    c.close();

                    //profile.setImageURI(selectedImageUri);
                }
                else
                {
                    Toast.makeText(getLocation, "Error", Toast.LENGTH_SHORT).show();
                }*/
            }
        }
    }
}