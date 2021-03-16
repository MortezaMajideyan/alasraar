package com.pattern.sarketab.ui.auth;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pattern.alasraar.R;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.data.remote.model.PayResult;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;
import com.pattern.sarketab.ui.Modle.M_sign_in;
import com.pattern.sarketab.ui.main.MainActivity;
import com.pattern.sarketab.ui.main.MainResource;
import com.pattern.sarketab.ui.main.MainViewModel;
import com.pattern.sarketab.ui.payment.ActivityPay;
import com.pattern.sarketab.ui.payment.ActivityPayMonth;
import com.pattern.sarketab.ui.retro.App;
import com.pattern.sarketab.ui.retro.Method_Conection;
import com.pattern.sarketab.viewmodels.ViewModelProviderFactory;
import com.thebrownarrow.customfont.CustomFontEditText;
import com.thebrownarrow.customfont.CustomFontTextView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kavak ;)
 */
public class AuthActivity extends DaggerAppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private static final String TAG = "AuthActivity";
    @Inject
    LocalStorage localStorage;
    @Inject
    ViewModelProviderFactory providerFactory;
    AuthViewModel viewModel;
    CustomFontEditText email , pass;
    int signType = -1;
    RelativeLayout pay_btn;
    RelativeLayout back;
    Dialog dialog;
    RelativeLayout dialogBtn;
    CustomFontTextView dialogContent;
    CustomFontTextView dialogTextBtn;
    CustomFontTextView dialogTitle;
    String regex = "(0|\\+98)?([ ]|,|-|[()]){0,2}9[1|2|3|4]([ ]|,|-|[()]){0,2}(?:[0-9]([ ]|,|-|[()]){0,2}){8}";
    SignInButton signInButton;
    int RC_SIGN_IN =100;
    String mail_text ="", pass_text="";
    ProgressDialog progressDialog;
    GoogleApiClient mGoogleApiClient;
    String sign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

      init();

        pay_btn.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this ,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mail_text = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass_text = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    void initDialog(Context context){

        dialog = new Dialog(context,R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialogContent = dialog.findViewById(R.id.dialog_content);
        dialogBtn = dialog.findViewById(R.id.update_btn);
        dialogTextBtn = dialog.findViewById(R.id.dialog_btn_text);
        dialogTitle = findViewById(R.id.dialogTitle);
    }

    public void init(){
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signInButton = findViewById(R.id.sign_in_button);
        pay_btn = findViewById(R.id.pay_btn);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfolly, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            HashMap<String ,String> map_sign = new HashMap<>();
            map_sign.put("mail" , acct.getEmail());
            map_sign.put("pass" , "12345");
            sign_in(map_sign);
        } else {
            Toast.makeText(this, getResources().getString(R.string.alert_error), Toast.LENGTH_SHORT).show();
            Log.e("morteza",result.toString());

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_btn:
                if (mail_text.indexOf("@") > 0 && pass_text.length() > 2){
                    if (mail_text.length() > 3){
                        String[] mail = mail_text.split("@");
                        HashMap<String ,String> map_sign = new HashMap<>();
                        map_sign.put("mail" , mail_text);
                        map_sign.put("pass" , pass_text);
                        if (mail.length > 1 && mail[0].length() > 1 && pass_text.length() > 2) {
                            sign_in(map_sign);
                        }
                    }else{
                        Toast.makeText(this, getResources().getString(R.string.insert_email_valid), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    if (mail_text.indexOf("@") <= 0 && pass_text.length() > 2){
                        Toast.makeText(this, getResources().getString(R.string.insert_email_valid), Toast.LENGTH_SHORT).show();
                    }else if (pass_text.length() <= 2 && mail_text.indexOf("@") > 0){
                        Toast.makeText(this, getResources().getString(R.string.insert_valid_password), Toast.LENGTH_SHORT).show();
                    }else if (pass_text.length() <= 2 && mail_text.indexOf("@") <=0){
                        Toast.makeText(this, getResources().getString(R.string.insert_valid_mail_pass), Toast.LENGTH_SHORT).show();
                    }
                }

        }
    }


    public void sign_in(final HashMap<String , String> map_sign){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.show();
        Method_Conection service = App.getApplication().create(Method_Conection.class);
        final Call<List<M_sign_in>> sign_in  = service.sign_in(map_sign);
        sign_in.enqueue(new Callback<List<M_sign_in>>() {
            @Override
            public void onResponse(Call<List<M_sign_in>> call, Response<List<M_sign_in>> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    sign = response.body().get(0).message;

                    if (sign.equals("ok")){
                        Intent i = new Intent(AuthActivity.this , MainActivity.class);
                        startActivity(i);
                        set_sign_shared(map_sign.get("mail"));
                        finish();
                    }else{
                        Intent i = new Intent(AuthActivity.this , MainActivity.class);
                        startActivity(i);
                        set_sign_shared(map_sign.get("mail"));
                        finish();
                    }
                }else{
                    Intent i = new Intent(AuthActivity.this , MainActivity.class);
                    startActivity(i);
                    set_sign_shared(map_sign.get("mail"));
                    finish();
                }

            }

            @Override
            public void onFailure(Call<List<M_sign_in>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AuthActivity.this, getResources().getString(R.string.insert_detail), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void set_sign_shared(String mail){
        SharedPreferences shared = getSharedPreferences("zerone" , MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("mail" , mail);
        editor.apply();
        editor.commit();
    }
}
