package com.app.partyplanets.DashBoad;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Activity.ChangePassword;
import com.app.partyplanets.Activity.GetLocation;
import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    private EditText edit_fName,edit_lname,edit_address1,edit_address2,edit_city,edit_state,edit_contact;
    CircleImageView edit_profile;
    Button edit_update;
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
    ImageView backprecc,edit_imgupdate;
    int SELECT_PICTURE = 200;
    String imgProfile="";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };















    private TextView name,email,address,contact,changepassword;
     CircleImageView profile;
    Button btnsignin,btn_changepassword;
    String secureloginResponse;

    String id;
    RelativeLayout relative_edit_img,relative_edit_details,show_details;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */)
        {
            @Override
            public void handleOnBackPressed() {
                // getActivity().onBackPressed();
                startActivity(new Intent(getActivity(),DashBoad.class));
                getActivity().finish();
               /* Fragment dasboadfragment=new Dasboadfragment();
                Fragment fragment = new Dasboadfragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               // fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.add(dasboadfragment, "detail") // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
                        .addToBackStack(null)
                        .commit();
             //   fragmentTransaction.commit();
*/
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        GetProfile(v);
        return v;}

     public void  GetProfile(View v)
    {  btn_changepassword=v.findViewById(R.id.btn_changepassword);
        loader = new Loader(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        btn_changepassword.setOnClickListener(this);
        name=v.findViewById(R.id.name);
        email=v.findViewById(R.id.email);
        address=v.findViewById(R.id.address);
        contact=v.findViewById(R.id.contact);
        profile=v.findViewById(R.id.profilerimg);
        btnsignin=v.findViewById(R.id.btnsignin);
        show_details=v.findViewById(R.id.show_details);
        btnsignin.setOnClickListener(this);
        SharedPreferences myPreferences = getActivity().getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
         secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        id = securelogincheckResponse.getId();
        String Fname = securelogincheckResponse.getFirstName();
        String Lname = securelogincheckResponse.getLastName();
        String pic = securelogincheckResponse.getImage();
        name.setText(Fname+""+Lname);
        address.setText(securelogincheckResponse.getState()+" "+securelogincheckResponse.getCity()+"and"+securelogincheckResponse.getAddress1()+""+securelogincheckResponse.getAddress2());
        contact.setText(securelogincheckResponse.getPhoneNo());
        email.setText(securelogincheckResponse.getEmail());
        Glide.with(getActivity())
                .load(ApplicationConstant.INSTANCE.baseUrl+pic)
                .into(profile);

        /////////////////////////////////////////////////////////////////
                                 //Update profile//
        edit_imgupdate=v.findViewById(R.id.edit_imgupdate);
        edit_fName=v.findViewById(R.id.edit_fName);
        edit_lname=v.findViewById(R.id.edit_lname);
        edit_address1=v.findViewById(R.id.edit_address1);
        edit_address2=v.findViewById(R.id.edit_address2);
        edit_city=v.findViewById(R.id.edit_city);
        edit_state=v.findViewById(R.id.edit_state);
        edit_contact=v.findViewById(R.id.edit_contact);
        edit_profile=v.findViewById(R.id.edit_profile);
        edit_update=v.findViewById(R.id.edit_update);
        edit_update.setOnClickListener(this);
        edit_imgupdate.setOnClickListener(this);
        relative_edit_img=v.findViewById(R.id.relative_edit_img);
        relative_edit_details=v.findViewById(R.id.relative_edit_details);
        edit_fName.setText(securelogincheckResponse.getFirstName());
        edit_lname.setText(securelogincheckResponse.getLastName());
        edit_address1.setText(securelogincheckResponse.getAddress1());
        edit_address2.setText(securelogincheckResponse.getAddress2());
        edit_city.setText(securelogincheckResponse.getCity());
        edit_state.setText(securelogincheckResponse.getState());
        edit_contact.setText(securelogincheckResponse.getPhoneNo());
        Log.d("dfhjhf",pic);
        if (pic.equalsIgnoreCase(".uploads/profile_picture/default.jpg"))
        {

        }
        else
        {
            Glide.with(getActivity())
                    .load(ApplicationConstant.INSTANCE.baseUrl+pic)
                    .into( edit_profile);

        }


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
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
        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
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
      @Override
    public void onClick(View v)
      {
        if (v==btnsignin)
        {   // startActivity(new Intent(getActivity(), SignUp.class).putExtra("type",2));//type 2 for update profile
            /*Intent i=new Intent(getActivity(),UpdateProfile.class);
            i.putExtra("secureloginResponse",secureloginResponse);
            startActivity(i);*/
            btnsignin.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            show_details.setVisibility(View.GONE);
            relative_edit_img.setVisibility(View.VISIBLE);
            relative_edit_details.setVisibility(View.VISIBLE);
        }
     if (v==edit_update)
     {
         profileUpdate();
     }

       if (v==edit_imgupdate)
      {
          verifyStoragePermissions();
          chooseImage();
      }

        if (v==btn_changepassword)
        {

            startActivity(new Intent(getActivity(), ChangePassword.class));
        }


    }

   /* public void editprofile(View v)
    {   edit_imgupdate=v.findViewById(R.id.edit_imgupdate);
        edit_fName=v.findViewById(R.id.edit_fName);
        edit_lname=v.findViewById(R.id.edit_lname);
        edit_address1=v.findViewById(R.id.edit_address1);
        edit_address2=v.findViewById(R.id.edit_address2);
        edit_city=v.findViewById(R.id.edit_city);
        edit_state=v.findViewById(R.id.edit_state);
        edit_contact=v.findViewById(R.id.edit_contact);
        edit_profile=v.findViewById(R.id.edit_profile);
        edit_update=v.findViewById(R.id.edit_update);
        edit_update.setOnClickListener(this);
        edit_imgupdate.setOnClickListener(this);
        relative_edit_img=v.findViewById(R.id.relative_edit_img);
        relative_edit_details=v.findViewById(R.id.relative_edit_details);


        String Fname = securelogincheckResponse.getFirstName();
        edit_fName.setText(securelogincheckResponse.getFirstName());
        edit_lname.setText(securelogincheckResponse.getLastName());
        edit_address1.setText(securelogincheckResponse.getAddress1());
        edit_address2.setText(securelogincheckResponse.getAddress2());
        edit_city.setText(securelogincheckResponse.getCity());
        edit_state.setText(securelogincheckResponse.getState());
        edit_contact.setText(securelogincheckResponse.getPhoneNo());

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
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
        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
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

    }*/




    public void profileUpdate()
    {
        if (edit_fName.getText().toString().isEmpty()) {
            edit_fName.setError("Please enter first Name");
            edit_fName.requestFocus();
        }
        else if (edit_lname.getText().toString().isEmpty())
        {   edit_lname.setError("Please enter last Name");
            edit_lname.requestFocus();
        }
        else if (edit_address1.getText().toString().isEmpty())
        {   edit_address1.setError("Please enter Address");
            edit_address1.requestFocus();
        }
        else if (edit_address2.getText().toString().isEmpty())
        {   edit_address2.setError("Please enter Address");
            edit_address2.requestFocus();
        }
        else if (edit_city.getText().toString().isEmpty())
        {   edit_city.setError("Please enter City Name");
            edit_city.requestFocus();
        }
        else if (edit_state.getText().toString().isEmpty())
        {   edit_state.setError("Please enter state");
            edit_state.requestFocus();
        }else if (edit_contact.getText().toString().isEmpty())
        {   edit_contact.setError("Please Enter Phone Number");
            edit_contact.requestFocus();
        }
        else if(edit_contact.getText().toString().length()!=10)
        {
            edit_contact.setError("Please Enter Minimum  10 Digits ");
            edit_contact.requestFocus();
        }

        else if (UtilsMethod.INSTANCE.isNetworkAvialable(getActivity()) == false)
        {
            Toast.makeText(getActivity(), "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {

            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            Log.d("fkdjvnbvhkjvjc",securelogincheckResponse.getId());
            UtilsMethod.INSTANCE.UpdateProfile(getActivity(),loader,securelogincheckResponse.getId().toString(),edit_fName.getText().toString(),edit_lname.getText().toString()
                    ,edit_contact.getText().toString(),edit_city.getText().toString()
                    ,edit_state.getText().toString(),edit_address1.getText().toString()
                    ,edit_address2.getText().toString(),"123456", String.valueOf(latitude), String.valueOf(longitude),"",imgProfile);
        }

    }

    public void verifyStoragePermissions() {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    getActivity(),
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2)
            {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri)
                {
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImageUri, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String picturePath = c.getString(columnIndex);
                    c.close();
                    imgProfile = picturePath;

                    Log.d("djfhjyurtuyrt",""+imgProfile);
                    edit_profile.setImageURI(selectedImageUri);
                }

                /*if (null != selectedImageUri)
                {
                    Uri selectedImage = data.getData();
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
   /* public  void chooseImage() {

       // create an instance of the
       // intent of the type image
       Intent i = new Intent();
       i.setType("image/*");
       i.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
   }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    edit_profile.setImageURI(selectedImageUri);
                }
            }
        }

    }
*/



    }