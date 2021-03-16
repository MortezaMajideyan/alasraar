package com.pattern.sarketab.network.auth;
import com.pattern.sarketab.data.remote.model.Update;
import com.pattern.sarketab.data.remote.model.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kavak ;)
 */
public interface CheckUpdateApi {
    @GET("notification/update")
    Flowable<Update> getUser(
           @Query("version") String version,
           @Query("os") String os
    );
    @FormUrlEncoded
    @POST("pay/check")
    Flowable<User> checkPayment(
            @Field("user_id") String userId
    );
}
