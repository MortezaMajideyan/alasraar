package com.pattern.sarketab.di.auth;
import com.pattern.sarketab.network.auth.RegisterApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kavak ;)
 */

@Module
public class AuthModule {
    @Provides
    static RegisterApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(RegisterApi.class);
    }
}
