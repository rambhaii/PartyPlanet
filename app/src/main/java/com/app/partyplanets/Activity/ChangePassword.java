package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.Data.Data;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.ApplicationConstant;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
    TextView currentpassword,newpassword,cpassword;
    Button btnsignin;
    Loader loader;
    String secureloginResponse;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        GetId();
    }

    private void GetId() {

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        currentpassword=findViewById(R.id.currentpassword);
        newpassword=findViewById(R.id.newpassword);
        cpassword=findViewById(R.id.cpassword);
        btnsignin=findViewById(R.id.btnsignin);
        btnsignin.setOnClickListener(this);
        SharedPreferences myPreferences =getSharedPreferences(ApplicationConstant.INSTANCE.prefNamePref, MODE_PRIVATE);
        secureloginResponse = myPreferences.getString(ApplicationConstant.INSTANCE.Loginrespose, null);
        Data securelogincheckResponse = new Gson().fromJson(secureloginResponse, Data.class);
        //personal Info
            id = securelogincheckResponse.getId();
            Log.d("idid",id);
    }

    @Override
    public void onClick(View v)
    {
        if (v==btnsignin)
        {
            changePassword();
        }

    }

    private void changePassword()
    {
        if (currentpassword.getText().toString().isEmpty())
        {
            currentpassword.setError("Enter Current password");
            currentpassword.requestFocus();
        }
        else if (newpassword.getText().toString().isEmpty())
        {
            newpassword.setError("Enter New password ");
            newpassword.requestFocus();
        }
        else if (cpassword.getText().toString().isEmpty())
        {
            cpassword.setError("Enter confirms password");
            cpassword.requestFocus();
        }
        else if (!newpassword.getText().toString().equals(cpassword.getText().toString())) {
            cpassword.setError("Confirm password does not match");
            cpassword.requestFocus();
        }
        else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
        {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilsMethod.INSTANCE.changePassword(ChangePassword.this,loader,id,currentpassword.getText().toString(),newpassword.getText().toString(),cpassword.getText().toString());
        }

}
}