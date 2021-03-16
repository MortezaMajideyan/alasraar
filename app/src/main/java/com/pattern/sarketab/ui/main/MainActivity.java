package com.pattern.sarketab.ui.main;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.pattern.alasraar.R;

import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;
import com.pattern.sarketab.ui.ActivityAbjad;
import com.pattern.sarketab.ui.ActivityChange_name;
import com.pattern.sarketab.ui.ActivityEghamat;
import com.pattern.sarketab.ui.ActivityEzdevaj;
import com.pattern.sarketab.ui.ActivityGhaeb;
import com.pattern.sarketab.ui.ActivityHajat;
import com.pattern.sarketab.ui.ActivityHamzad;
import com.pattern.sarketab.ui.ActivityInfo;
import com.pattern.sarketab.ui.ActivityMarg;
import com.pattern.sarketab.ui.payment.ActivityPay;
import com.pattern.sarketab.ui.Sarketab.ActivitySarketab;
import com.pattern.sarketab.ui.ActivitySetting;
import com.pattern.sarketab.ui.ActivitySherakat;
import com.pattern.sarketab.ui.month.MonthActivity;
import com.pattern.sarketab.ui.payment.ActivityPayMonth;
import com.pattern.sarketab.util.IabHelper;
import com.pattern.sarketab.viewmodels.ViewModelProviderFactory;
import com.thebrownarrow.customfont.CustomFontTextView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    LocalStorage localStorage;
    @Inject
    String string;
    public MainActivity activity;
    IabHelper mHelper;
    public static final String TAG = "MAinActivity";
    private CardView sarketab , shaks_ghaeb , hajat , hamzad , change_name;
    private CardView ezdevaj;
    private CardView abjad;
    private CardView sherakat;
    private CardView eghamat;
    private CardView marg;
    private RelativeLayout setting;
    private RelativeLayout about;
    private MainViewModel viewModel;
    private CustomFontTextView dialog_content;
    private RelativeLayout updateButton;
    private LinearLayout disable_click;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
//      AhmadPay ahmadPay = new AhmadPay.Builder().setContext(this)
//                .setUserId("2")
//                .setUrl("http://192.168.7.36/FarhangeNam/public/api/")
//                .setId("4")
//                .setPaymentSubject("BusinessNameReques")
//                .setOnPayListener(new PayListener() {
//                    @Override
//                    public void PayDone(String json,String paygiri) {
//                        Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "PayDone: "+json+"   "+paygiri);
//                    }
//
//                    @Override
//                    public void PayError(String error) {
//                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "PayDone: "+error);
//                    }
//                })
//                .build();
//        ahmadPay.start();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        checkUpdate();
        subscribeObservers();
        initView();


        disable_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(activity, "dsdd", Toast.LENGTH_SHORT).show();
            }
        });
        disable_click.setVisibility(View.GONE);
        sarketab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivitySarketab.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);
            }
        });
        ezdevaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivityEzdevaj.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);


            }
        });

        marg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivityMarg.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);

            }
        });
        abjad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivityAbjad.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);


            }
        });

        sherakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent( MainActivity.this, ActivitySherakat.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);




            }
        });

        shaks_ghaeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent( MainActivity.this, ActivityGhaeb.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);


            }
        });

        hajat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent( MainActivity.this, ActivityHajat.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);


            }
        });

        hamzad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent( MainActivity.this, ActivityHamzad.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);


            }
        });
        eghamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivityEghamat.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);



            }
        });

        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (disable_click.getVisibility() != View.VISIBLE){
                    Intent intent = new Intent(MainActivity.this, ActivityChange_name.class);
                    startActivity(intent);
                }
                disable_click.setVisibility(View.VISIBLE);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivitySetting.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityInfo.class);
                startActivity(intent);
            }
        });
        subscribePays();
        if (!localStorage.readMessage("userId").equals("")){
            viewModel.checkUserPay(localStorage.readMessage("userId"));
        }

    }

    public void initView(){
        sarketab = findViewById(R.id.sarketab);
        shaks_ghaeb = findViewById(R.id.shaks_ghaeb);
        ezdevaj = findViewById(R.id.ezdevaj);
        abjad = findViewById(R.id.abjad);
        sherakat = findViewById(R.id.sherakat);
        eghamat = findViewById(R.id.eghamat);
        setting =findViewById(R.id.setting);
        marg = findViewById(R.id.marg);
        about = findViewById(R.id.about);
        hajat = findViewById(R.id.hajat);
        hamzad = findViewById(R.id.hamzad);
        change_name = findViewById(R.id.change_name);
       dialog = new Dialog(MainActivity.this,R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog_content = dialog.findViewById(R.id.dialog_content);
        updateButton = dialog.findViewById(R.id.update_btn);
        disable_click = findViewById(R.id.disable_click);

    }

    private void subscribePays(){
        viewModel.observerPay().observe(this, new Observer<MainResource<User>>() {
            @Override
            public void onChanged(MainResource<User> userStates) {
                if (userStates!=null){
                    switch (userStates.status){
                        case ERROR:{

                            break;
                        }
                        case Update:{
                                if (userStates.data.getStatus()==200){
                                    Log.e(TAG, "Sarketab: "+String.valueOf(userStates.data.getResults().getSarketab()));
                                    Log.e(TAG, "Pishgoo: "+String.valueOf(userStates.data.getResults().getPishgoo()));
                                    if (userStates.data.getResults().getSarketab()==1){
                                        localStorage.writeMessage("true","payApp");
                                    }else if (userStates.data.getResults().getSarketab()==0){
                                        localStorage.writeMessage("false","payApp");
                                    }
                                    if (userStates.data.getResults().getPishgoo()==1){
                                        localStorage.writeMessage("true","payMonth");

                                    }else if (userStates.data.getResults().getPishgoo()==0){
                                        localStorage.writeMessage("false","payMonth");
                                    }
                                }
                            break;
                        }
                        case Updated:{
                            Log.d(TAG, "updated: "+userStates.message);
                            break;
                        }
                        case LOADING:{

                            break;
                        }

                    }
                }

            }
        });
    }








    private void subscribeObservers(){
        viewModel.observerUpdate().observe(this, new Observer<MainResource<Update>>() {
            @Override
            public void onChanged(MainResource<Update> userStates) {
                if (userStates!=null){
                    switch (userStates.status){
                        case ERROR:{
                            Log.d(TAG, "onChanged: "+userStates.message);
                            String version = getVersion(MainActivity.this);
                            if (!localStorage.readMessage("version").equals("")){
                            if (!version.equals(localStorage.readMessage("version"))) {
                                dialog_content.setText(localStorage.readMessage("updateContent"));
                                final String url = localStorage.readMessage("updateUrl");
                                updateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                        startActivity(browserIntent);
                                    }
                                });
                                dialog.show();
                            }
                            }
                            break;
                        }
                        case Update:{
                           localStorage.writeMessage(userStates.data.getResults().getContent(),"updateContent");
                           localStorage.writeMessage(userStates.data.getResults().getUrl(),"updateUrl");
                           localStorage.writeMessage(userStates.data.getResults().getVersion(),"version");
                            Log.d(TAG, "onChanged: "+userStates.data.getResults().getContent());
                            dialog_content.setText(userStates.data.getResults().getContent());
                           final String url = userStates.data.getResults().getUrl();
                            updateButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                        startActivity(browserIntent);
                                    }
                            });
                            dialog.show();
                            break;
                        }
                        case Updated:{
                            Log.d(TAG, "updated: "+userStates.message);
                            break;
                        }
                        case LOADING:{

                            break;
                        }

                    }
                }

            }
        });
    }
    private void checkUpdate(){
        String os = "android";
        String version = getVersion(this);
        if (version!=null)
        viewModel.checkUpdate(version,os);

    }


    private String getVersion(Context context){
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
//    IabHelper.QueryInventoryFinishedListener mGotInventoryListener1
//            = new IabHelper.QueryInventoryFinishedListener() {
//        public void onQueryInventoryFinished(IabResult result,
//                                             Inventory inventory) {
//            BaseApplication.check =1;
//            if (result.isFailure()) {
//                // handle error here
//                Log.d(TAG, "onQueryInventoryFinished: "+ result.getMessage());
//            }
//            else {
//                // does the user have the premium upgrade?
//                boolean mIsPremium = inventory.hasPurchase(SKU_PREMIUM);
//                boolean mIsPremium1 = inventory.hasPurchase(SKU_PREMIUM1);
//                if (mIsPremium){
//                    Log.d(TAG, "onQueryInventoryFinished Month: premium");
//                    localStorage.writeMessage("true","payMonth");
//                }else {
//                    Log.d(TAG, "onQueryInventoryFinished Month: not premium");
//                    localStorage.writeMessage("false","payMonth");
//                }
//                if (mIsPremium1){
//                    Log.d(TAG, "onQueryInventoryFinished Sarketab: premium");
//                    localStorage.writeMessage("true","payApp");
//                }else {
//                    Log.d(TAG, "onQueryInventoryFinished Sarketab: not premium");
//                    localStorage.writeMessage("false","payApp");
//                }
//                // update UI accordingly
//            }
//        }
//    };


    @Override
    protected void onPostResume() {
        super.onPostResume();
        disable_click.setVisibility(View.GONE);
    }
}
