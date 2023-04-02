package com.app.partyplanets.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.Loader;
import com.app.partyplanets.Utils.UtilsMethod;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView signup, tv_Google,email;
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInOptions gso;
    private static int SIGN_IN = 100;
    public static String Email;

    TextView facebook;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new Loader(this, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.activity_login);
        GetId();
    }

    @SuppressLint("WrongViewCast")
    private void GetId() {



        signup = findViewById(R.id.signup);
        tv_Google = findViewById(R.id.tv_Google);
        email=findViewById(R.id.email);
        email.setOnClickListener(this);
        tv_Google.setOnClickListener(this);
        signup.setOnClickListener(this);
        facebook=findViewById(R.id.facebook);
         facebook.setOnClickListener(this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();



    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("kjdfjk","jvnvb");
    }
*/


    @Override
    public void onClick(View v)
    {
        if (v == tv_Google)
        {
            signIn();
        }
        if (v == signup)
        {
            startActivity(new Intent(Login.this, SignUp.class).putExtra("type","1"));//type 1 for Register user
        }
        if (v==email)
        {
        startActivity(new Intent(Login.this,SignInEmail.class));
        }

        if (v==facebook)
        {
                LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("public_profile"));
                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>()
                        {
                            @Override
                            public void onSuccess(LoginResult loginResult)
                            {
                                 Log.d("dffgg","sfhghj");
                                startActivity(new Intent(Login.this,SignUp.class).putExtra("type","2"));
                            }

                            @Override
                            public void onCancel() {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception)
                            {
                                // App code
                            }
                        });








        }


    }

    private void signIn()
    {
        Intent SigninIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(SigninIntent, SIGN_IN);

    }
    Loader loader;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    { super.onActivityResult(requestCode, resultCode, data);


        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        if (requestCode == SIGN_IN)
        {
            try {
                task.getResult(ApiException.class);
                Log.e("dsdfsdf",task.getResult(ApiException.class).getEmail());
                loader.show();
                UtilsMethod.INSTANCE.signIn(Login.this,loader,task.getResult(ApiException.class).getEmail(),"",1);

                }
            catch (ApiException e)
            {
                Toast.makeText(this, "sdmnjv" + e, Toast.LENGTH_SHORT).show();
            }
        } else if (!(requestCode==SIGN_IN))
        {
            startActivity(new Intent(Login.this,SignUp.class).putExtra("type","3"));        }
        else {
            Toast.makeText(this, "dfgjkg", Toast.LENGTH_SHORT).show();
        }
}
}
