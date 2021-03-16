package com.pattern.sarketab.ahmadpay;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.pattern.sarketab.ahmadpay.network.PayListener;
/**
 * Created by kavak ;)
 */
public class AhmadPay extends AppCompatActivity {
    private static final String TAG = "AhmadPay";
    Context context;
    private String url;
    private String paymentSubject;
    private String userId;
    private String id;
    public static PayListener payListener;
    public void start(){
        if (context!=null) {
            if (url!=null && paymentSubject!=null ){
                    Intent intent = new Intent(context, AhmadPayActivity.class);
                    intent.putExtra("url",url);
                    intent.putExtra("paymentSubject",paymentSubject);
                    context.startActivity(intent);
            }else {
                Log.e(TAG, "startActivity: Some Value missed");
            }
        }else {
            throw new RuntimeException("Something failed.", new Throwable(String.valueOf("Context not found")));
        }
    }

    public AhmadPay(Context context, String url, String paymentSubject,PayListener payListener) {
        this.context = context;
        this.url = url;
        this.paymentSubject = paymentSubject;
        AhmadPay.payListener = payListener;
    }

    public static class Builder {
        private String url;
        private String paymentSubject;
        private Context context;
        private PayListener payListener;
        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }
        public Builder setOnPayListener(PayListener payListener) {
            this.payListener = payListener;
            return this;
        }

        public Builder setPaymentSubject(String paymentSubject) {
            this.paymentSubject = paymentSubject;
            return this;
        }


        public AhmadPay build() {
            return new AhmadPay(context,url,paymentSubject,payListener);
        }
    }
}
