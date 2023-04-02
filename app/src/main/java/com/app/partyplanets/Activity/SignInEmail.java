package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;

public class SignInEmail extends AppCompatActivity implements View.OnClickListener {
    private EditText email,password;
    private TextView btnsignin,forgetpassword;
    Loader loader;
    private ImageView backprecc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);
        GetID();

    }

    private void GetID()
    {   forgetpassword=findViewById(R.id.forgetpassword);
        backprecc=findViewById(R.id.backprecc);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnsignin=findViewById(R.id.btnsignin);
        forgetpassword.setOnClickListener(this);
        btnsignin.setOnClickListener(this);
        backprecc.setOnClickListener(this);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    public void onClick(View v)
    {
        if(v==btnsignin)
        {
            SignIn();
        }
        if (v==backprecc)
        {   startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
        if (v==forgetpassword)
        {
            forgetpassword();
        }

    }

    private void forgetpassword()
    {
        startActivity(new Intent(SignInEmail.this,ForgotPassword.class));
    }

    private void SignIn()
    {
        if (email.getText().toString().isEmpty()) {
            email.setError("Please enter email Id");
            email.requestFocus();
        }
        else if (password.getText().toString().isEmpty())
        {   password.setError("Please enter password");
            password.requestFocus();
         }
        else if (UtilsMethod.INSTANCE.isValidEmail(email.getText().toString()) == false)
        {
            //Toast.makeText(this, "Please Enter valid E-mail Id", Toast.LENGTH_SHORT).show();
            email.setError("Please Enter valid E-mail Id");
            email.requestFocus();
        }
        else if (UtilsMethod.INSTANCE.isNetworkAvialable(this) == false)
        {
            Toast.makeText(this, "Please Check Your Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        else
        {   loader.show();
            loader.setCancelable(false);
            loader.setCanceledOnTouchOutside(false);
            UtilsMethod.INSTANCE.signIn(SignInEmail.this,loader,email.getText().toString(),password.getText().toString(),0);
        }

    }
}