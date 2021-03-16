package com.pattern.sarketab.network.month;

import com.pattern.sarketab.data.remote.model.Month;
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
public interface GetMonthInfo {
    @GET("app/months")
    Flowable<Month> getMonths(
            @Query("gate") String gate,
            @Query("user_id") String userId
    );

}
