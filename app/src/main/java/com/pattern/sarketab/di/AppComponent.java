package com.pattern.sarketab.di;

import android.app.Application;

import com.pattern.sarketab.ActivityBuildersModule1;
import com.pattern.sarketab.BaseApplication;
import com.pattern.sarketab.data.preferences.LocalStorage;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {
    LocalStorage localStorage();
    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}







