package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;

public class OtpVerification extends AppCompatActivity implements View.OnClickListener {
    TextView email, otp, newpassword, cpassword;
    Button btnsignin;
    Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        GetId();
    }

    private void GetId() {
        email = findViewById(R.id.email);
        otp = findViewById(R.id.otpverify);
        newpassword = findViewById(R.id.newpassword);
        cpassword = findViewById(R.id.cpassword);
        btnsignin = findViewById(R.id.btnsignin);
        btnsignin.setOnClickListener(this);

        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);


    }

    @Override
    public void onClick(View v) {
        if (v == btnsignin) {
            verifyOtp();
        }

    }

    private void verifyOtp() {
        if (email.getText().toString().isEmpty()) {
            email.setError("Enter E-mail ID");
            email.requestFocus();
        } else if (otp.getText().toString().isEmpty()) {
            otp.setError("Please Enter a valid otp ");
            otp.requestFocus();
        } else if (newpassword.getText().toString().isEmpty()) {
            newpassword.setError("Enter password");
            newpassword.requestFocus();
        } else if (cpassword.getText().toString().isEmpty()) {
            cpassword.setError("Enter password ");
            cpassword.requestFocus();
        } else if (!newpassword.getText().toString().equals(cpassword.getText().toString())) {
            cpassword.setError("Confirm password does not match");
            cpassword.requestFocus();
        } else if (UtilsMethod.INSTANCE.isValidPassword(newpassword.getText().toString()) == false) {
            newpassword.setError("Enter at least one special character , lower case , upper case and digit ");
            newpassword.requestFocus();
        } else if (UtilsMethod.INSTANCE.isValidEmail(email.getText().toString()) == false) {
            //Toast.makeText(this, "Please Enter valid E-mail Id", Toast.LENGTH_SHORT).show();
            email.setError("Please Enter valid E-mail Id");
            email.requestFocus();
        } else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false) {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        } else {
            loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            Log.d("otp",otp.getText().toString());
            UtilsMethod.INSTANCE.otpVerify(OtpVerification.this,loader,email.getText().toString(),otp.getText().toString(),newpassword.getText().toString(),cpassword.getText().toString());

        }
    }
}