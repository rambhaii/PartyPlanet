package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    Button submit;
    TextView emailId;
    Loader loader;
    ImageView backprecc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        GetId();

    }

    private void GetId()
    {   backprecc=findViewById(R.id.backprecc);
        submit=findViewById(R.id.submit);
        emailId=findViewById(R.id.emailId);
        backprecc.setOnClickListener(this);
        submit.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    public void onClick(View v)
    {
     if (v==submit)
       {
           forgetpassword();
       }
     if (v==backprecc)
     {
         startActivity(new Intent(ForgotPassword.this,SignInEmail.class));
         finish();
     }

    }

    private void forgetpassword()
    {
        if (emailId.getText().toString().isEmpty())
        {
            emailId.setError("Please enter E-mail Id");
            emailId.requestFocus();
        }
        else if(UtilsMethod.INSTANCE.isValidEmail(emailId.getText().toString()) == false)
        {
            emailId.setError("Please Enter valid E-mail Id");
            emailId.requestFocus();
        }
        else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
        {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {   loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilsMethod.INSTANCE.forgetpasswor(ForgotPassword.this,loader,emailId.getText().toString());
        }

    }
}