package com.pattern.sarketab.di.pay;




import com.pattern.sarketab.network.auth.CheckUpdateApi;
import com.pattern.sarketab.network.pay.CheckPay;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kavak ;)
 */

@Module
public class PayModule {

    @Provides
    static CheckPay provideAuthApi(Retrofit retrofit){
        return retrofit.create(CheckPay.class);
    }
}
