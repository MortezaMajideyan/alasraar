package com.pattern.sarketab;


import com.pattern.sarketab.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * Created by kavak ;)
 */
public class BaseApplication extends DaggerApplication {

    public static int check=0;


    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
