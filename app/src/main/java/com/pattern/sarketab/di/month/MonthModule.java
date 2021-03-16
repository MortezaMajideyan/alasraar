package com.pattern.sarketab.di.month;


import com.pattern.sarketab.network.month.GetMonthInfo;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kavak ;)
 */
@Module
public class MonthModule {
    @Provides
    static GetMonthInfo provideAuthApi(Retrofit retrofit){
        return retrofit.create(GetMonthInfo.class);
    }
}
