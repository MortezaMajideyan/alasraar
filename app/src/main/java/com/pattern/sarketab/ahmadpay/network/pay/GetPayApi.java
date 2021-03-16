package com.pattern.sarketab.ahmadpay.network.pay;




import com.pattern.sarketab.ahmadpay.models.pay.PayData;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kavak ;)
 */
public interface GetPayApi {
    @FormUrlEncoded
    @POST("pay/request")
    Flowable<PayData> getPayUrl(
            @Field("device_id") String deviceId,
            @Field("case") String paymentSubject,
            @Field("id") String id
    );
}
