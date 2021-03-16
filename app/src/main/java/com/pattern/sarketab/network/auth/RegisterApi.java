package com.pattern.sarketab.network.auth;


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
public interface RegisterApi {
    @FormUrlEncoded
    @POST("pay/set")
    Flowable<PayResult> payDone(
            @Field("user_id") String userId,
            @Field("pay_id") String payId
    );
    @FormUrlEncoded
    @POST("user/register")
    Flowable<User> registerUser(
            @Field("pnumber") String phoneNumber,
            @Field("force") String force
    );
    @FormUrlEncoded
    @POST("user/confirm")
    Flowable<User> confirmUser(
            @Field("pnumber") String phoneNumber,
            @Field("verification_code") String verificationCode
    );



}
