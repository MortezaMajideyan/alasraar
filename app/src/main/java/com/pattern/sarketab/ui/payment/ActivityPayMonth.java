package com.pattern.sarketab.ui.payment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pattern.alasraar.R;
import com.pattern.sarketab.ahmadpay.AhmadPay;
import com.pattern.sarketab.ahmadpay.network.PayListener;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.data.remote.model.PayPackage;
import com.pattern.sarketab.data.remote.model.PayResult;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.ui.auth.AuthActivity;
import com.pattern.sarketab.ui.main.MainResource;
import com.pattern.sarketab.util.Constants;
import com.pattern.sarketab.viewmodels.ViewModelProviderFactory;
import com.thebrownarrow.customfont.CustomFontTextView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


/**
 * Created by kavak ;)
 */
public class ActivityPayMonth extends DaggerAppCompatActivity {
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    LocalStorage localStorage;
    PayViewModel viewModel;
    // Debug tag, for logging
    LinearLayout priceLayout;
    RelativeLayout priceOffLayout;
    CustomFontTextView price;
    CustomFontTextView priceOff;
    private static final String TAG = "ActivityPay";
    CustomFontTextView title;
    CustomFontTextView contenttxt;
    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_PREMIUM = "pishgooMonth";
    RelativeLayout loading;
    // Does the user have the premium upgrade?
    boolean mIsPremium = false;
    int error = 0;
    CustomFontTextView signBefore;
    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST =1372 ;
    Dialog dialog;
    RelativeLayout dialogBtn;
    CustomFontTextView dialogContent;
    // The helper object
    RelativeLayout button;
    CustomFontTextView dialogTextBtn;
    CustomFontTextView dialogTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        title = findViewById(R.id.title);
        button = findViewById(R.id.pay_btn);
        contenttxt = findViewById(R.id.contentInfo);
        loading = findViewById(R.id.loading);
        viewModel = ViewModelProviders.of(this, providerFactory).get(PayViewModel.class);
        title.setText(getResources().getString(R.string.monthShterak));
        contenttxt.setText(getResources().getString(R.string.pishgoo_shterak_info));
        signBefore = findViewById(R.id.payBefore);
        priceLayout = findViewById(R.id.priceLayout);
        priceOffLayout = findViewById(R.id.priceOffLayout);
        price = findViewById(R.id.price);
        priceOff = findViewById(R.id.priceOff);
        viewModel.getPackageInfo("pishgoo");
        subscribeObservers();
        subscribePackageObservers();
        signBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPayMonth.this, AuthActivity.class);
                intent.putExtra("payType","");
                startActivity(intent);
            }
        });
// You can find it in your Bazaar console, in the Dealers section.
// It is recommended to add more security than just pasting it in your source code;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AhmadPay ahmadPay =new  AhmadPay.Builder().setContext(ActivityPayMonth.this)
                        .setPaymentSubject("pishgoo")
                        .setUrl(Constants.BASE_URL).
                                setOnPayListener(new PayListener() {
                                    @Override
                                    public void PayDone(String json, String paygiri,final String payId) {
                                        if (!localStorage.readMessage("userId").equals("")){
                                            initDialog(ActivityPayMonth.this);
                                            dialogTextBtn.setText("ثبت پرداخت");
                                            dialogContent.setText("کد پیگیری "+paygiri);
                                            dialog.show();
                                            dialogBtn.setVisibility(View.GONE);
                                            localStorage.writeMessage("true","payMonth");
                                            viewModel.payed(localStorage.readMessage("userId"),payId);
                                        }else {
                                            initDialog(ActivityPayMonth.this);
                                            dialog.setCancelable(false);
                                            dialog.show();
                                            dialogTextBtn.setText("ثبت نام");
                                            dialogContent.setText("جهت ذخیره ی اطلاعات پرداخت ثبت نام کنید");
                                            dialogBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(ActivityPayMonth.this, AuthActivity.class);
                                                    intent.putExtra("payType","Month");
                                                    intent.putExtra("PayId",payId);
                                                    startActivity(intent);
                                                    finish();
                                                    localStorage.writeMessage("true","payMonth");
                                                }
                                            });
                                        }
                                    }
                                    @Override
                                    public void PayError(String error) {
                                        Toast.makeText(ActivityPayMonth.this, "پرداخت ناموفق", Toast.LENGTH_SHORT).show();
                                    }
                                }).build();
            ahmadPay.start();

            }
        });
        Log.d(TAG, "Starting setup.");




    }


    void initDialog(Context context){
        dialog = new Dialog(context,R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pay);
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

    private void subscribeObservers(){
        viewModel.observerPay().observe(this, new Observer<MainResource<PayResult>>() {
            @Override
            public void onChanged(MainResource<PayResult> userStates) {
                if (userStates!=null){
                    switch (userStates.status){
                        case ERROR:{
                            Log.d(TAG, "onChanged: "+userStates.message);

                            break;
                        }
                        case Update:{
                            dialog.dismiss();
                            Toast.makeText(ActivityPayMonth.this, "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        }
                        case Updated:{
                            Log.d(TAG, "updated: "+userStates.message);
                            break;
                        }
                        case LOADING:{
                            Toast.makeText(ActivityPayMonth.this, "کمی صبور باشید ...", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }
                }

            }
        });
    }

    private void PayedMoney(String payId){
        viewModel.payed(localStorage.readMessage("userId"),payId);
    }
    private void subscribePackageObservers(){
        viewModel.observerPackage().observe(this, new Observer<MainResource<PayPackage>>() {
            @Override
            public void onChanged(MainResource<PayPackage> userStates) {
                if (userStates!=null){
                    switch (userStates.status){
                        case ERROR:{
                            Log.d(TAG, "onChanged: "+userStates.message);
                            loading.setVisibility(View.GONE);

                            break;
                        }
                        case Update:{
                            contenttxt.setText(userStates.data.getResults().getDescription());
                            int offNum = Integer.valueOf(userStates.data.getResults().getOff());
                            int priceNum = Integer.valueOf(userStates.data.getResults().getPrice());
                            priceOff.setText(userStates.data.getResults().getPrice());
                            price.setText(String.valueOf(priceNum-(priceNum*offNum)/100));
                            loading.setVisibility(View.GONE);
                            if (offNum==0){
                                priceOffLayout.setVisibility(View.GONE);
                            }
                            break;
                        }
                        case Updated:{
                            Log.d(TAG, "updated: "+userStates.message);
                            break;
                        }
                        case LOADING:{
                            loading.setVisibility(View.VISIBLE);
                            break;
                        }

                    }
                }

            }
        });
    }
}
