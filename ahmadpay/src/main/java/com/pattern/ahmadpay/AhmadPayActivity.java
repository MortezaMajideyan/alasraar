package com.pattern.ahmadpay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.pattern.ahmadpay.models.pay.PayData;
import com.pattern.ahmadpay.network.pay.GetPayApi;

import java.util.Observable;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by kavak ;)
 */
public class AhmadPayActivity extends AppCompatActivity {
    private static final String TAG = "AhmadPayActivity";
    Retrofit retrofit;
    SingleTonClass singleTonClass;
    private ProgressDialog dialog;
    ProgressBar progressBar;
    boolean isDone = false;
    WebView webView;
    private MediatorLiveData<Resource<PayData>> payData = new MediatorLiveData<>();
    GetPayApi getPayApi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ahmadpay);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        singleTonClass =SingleTonClass.getInstance();
        dialog = new ProgressDialog(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "Android");
        retrofit = singleTonClass.prvideRetrofitInstance(getIntent().getStringExtra("url"));
        getPayApi = retrofit.create(GetPayApi.class);
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        subscribeObserver();
        getUrl(android_id,getIntent().getStringExtra("paymentSubject"));
    }

    public void getUrl(String id,String paymentSubject){
        payData.setValue(Resource.loading((PayData) null));
        final LiveData<Resource<PayData>> resourceLiveData = LiveDataReactiveStreams.fromPublisher(
                getPayApi.getPayUrl(id,paymentSubject,"0")
                        .onErrorReturn(new Function<Throwable, PayData>() {
                            @Override
                            public PayData apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: "+throwable.getMessage() );
                                PayData payData = new PayData();
                                payData.setStatus(-1);
                                return payData;
                            }
                        })
                        .map(new Function<PayData, Resource<PayData>>() {
                            @Override
                            public Resource<PayData> apply(PayData payData) throws Exception {
                                if (payData.getStatus()!=-1) {
                                    return Resource.done(payData);
                                }else {
                                    return Resource.error("Could not check for url", null);
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
        payData.addSource(resourceLiveData, new Observer<Resource<PayData>>() {
            @Override
            public void onChanged(Resource<PayData> payDataResource) {
                payData.setValue(payDataResource);
                payData.removeSource(resourceLiveData);
            }
        });

    }

    public LiveData<Resource<PayData>> observerUrl(){
        return payData;
    }

    private void subscribeObserver(){
       observerUrl().observe(this, new Observer<Resource<PayData>>() {
            @Override
            public void onChanged(Resource<PayData> payDataResource) {
                if (payDataResource!=null){
                    switch (payDataResource.status){
                        case Updated:
                            progressBar.setVisibility(View.GONE);
                            break;

                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            AhmadPay.payListener.PayError("error happened");
                            Log.e(TAG, "onChanged: Error");
                            finish();
                            break;

                        case Done:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: "+payDataResource.data.getUrl().getUrl());
                            startWebView(payDataResource.data.getUrl().getUrl());
//                            Intent intent = new Intent(PayActivity.this, MainActivity.class);
//                            intent.putExtra("url",payDataResource.data.getUrl().getUrl());
//                            startActivity(intent);
                           break;
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                    }

                }
            }
        });
    }


    private void startWebView(String url) {
        dialog.setMessage("لطفا کمی صبور باشید..");
        dialog.show();
        Log.e(TAG, "startWebView: ");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        //Load url in webView

        webView.loadUrl(url);
    }
    @JavascriptInterface
    public void setPayInfo(String json,String paygiri,String payId) {
        Log.e(TAG, "setPayInfo: "+json);
        if (json.equals("200")||json.equals("201")) {
            AhmadPay.payListener.PayDone(json, paygiri,payId);
            isDone = true;
            finish();
        }else {
            AhmadPay.payListener.PayError("an error happened");
            isDone = false;
            finish();
        }

    }


    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: ");
        if (isDone) {
            super.onBackPressed();
        }else {
            AhmadPay.payListener.PayError("error happened");
            super.onBackPressed();
        }
    }
}
