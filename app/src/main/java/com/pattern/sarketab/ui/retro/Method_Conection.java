package com.pattern.sarketab.ui.retro;

import com.pattern.sarketab.ui.Modle.M_map;
import com.pattern.sarketab.ui.Modle.M_sarketab;
import com.pattern.sarketab.ui.Modle.M_sign_in;

import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface Method_Conection {

    // get all services building
    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_sign_in>> sign_in(@FieldMap HashMap<String , String> map_sign_in);


    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_sarketab>> sarketab(@Field("sarketab") String map_sign_in);


    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> married(@Field("marriage") String marriage);

    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> abjad_istikharah(@Field("abjad_istikharah") String abjad_istikharah);

    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> absent_status(@Field("absent_status") String absent_status);

    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> partnership(@Field("partnership") String partnership);


    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> need(@Field("need") String need);

    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> death(@Field("death") String death);

    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> location(@Field("location") String location);


    @FormUrlEncoded
    @POST("app.php")
    Call<List<M_map>> changename(@Field("changename") String changename);



}
