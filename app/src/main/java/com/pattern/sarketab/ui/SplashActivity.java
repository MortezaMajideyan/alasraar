package com.pattern.sarketab.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.pattern.alasraar.R;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.ui.auth.AuthActivity;
import com.pattern.sarketab.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
/**
 * Created by kavak ;)
 */
public class SplashActivity extends AppCompatActivity {
   // @Inject
   // LocalStorage localStorage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      /*if (get_sign_shared().equals("0")){
          Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
          intent.putExtra("payType","");
          startActivity(intent);
          finish();
      }else {
          Intent intent = new Intent(SplashActivity.this, MainActivity.class);
          startActivity(intent);
          finish();
      }*/

    }


    private String get_sign_shared(){
        try {
            SharedPreferences shared = getSharedPreferences("zerone", MODE_PRIVATE);
            String defaults = "0";
            String sign_state = shared.getString("mail", defaults);
            return sign_state;
        }catch (Exception e){
            return "0";
        }

    }


}
