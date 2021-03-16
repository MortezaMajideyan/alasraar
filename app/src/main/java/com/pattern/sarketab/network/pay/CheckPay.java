package com.pattern.sarketab.network.pay;

import com.pattern.sarketab.data.remote.model.PackageInfo;
import com.pattern.sarketab.data.remote.model.PayPackage;
import com.pattern.sarketab.data.remote.model.PayResult;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kavak ;)
 */
public interface CheckPay {
    @FormUrlEncoded
    @POST("pay/set")
    Flowable<PayResult> checkPay(
            @Field("user_id") String userIid,
            @Field("pay_id") String payId
    );
    @GET("pay/getPackage")
    Flowable<PayPackage> getPayInfo(
            @Query("case") String payTitle
    );
}
