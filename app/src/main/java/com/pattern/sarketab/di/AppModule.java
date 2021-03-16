package com.pattern.sarketab.di;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.pattern.alasraar.R;
import com.pattern.sarketab.data.preferences.LocalStorage;
import com.pattern.sarketab.data.preferences.SharedPrefStorage;
import com.pattern.sarketab.util.Constants;
import com.pattern.sarketab.util.IabHelper;
import com.pattern.sarketab.util.IabResult;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.ic_abjad)
                .error(R.drawable.ic_diamond);
    }





    @Provides
    @Singleton
    public LocalStorage provideLocalStorage(Application context){
        return new SharedPrefStorage(context);
        }

    @Singleton
    @Provides
    static Retrofit prvideRetrofitInstance(){

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getUnsafeOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }



    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer E55BQbciq6eKXDTfZjhtB8DHbXnrcHdVWXPWqd8NxEGryukXenQ47Dj6Rzwa")
                            .build();
                    return chain.proceed(newRequest);
                }
            });
            OkHttpClient okHttpClient = builder.build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Singleton
    @Provides
    static String someString(){
        return "String for testing injection";
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.ic_sarketab);
    }

}
