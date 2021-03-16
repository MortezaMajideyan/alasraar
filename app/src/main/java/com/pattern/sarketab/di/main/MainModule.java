package com.pattern.sarketab.di.main;




import com.pattern.sarketab.network.auth.CheckUpdateApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kavak ;)
 */

@Module
public class MainModule {

    @Provides
    static CheckUpdateApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(CheckUpdateApi.class);
    }
}
