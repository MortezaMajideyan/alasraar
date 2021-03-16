package com.pattern.sarketab.di;


import com.pattern.sarketab.di.auth.AuthModule;
import com.pattern.sarketab.di.auth.AuthViewModelsModule;
import com.pattern.sarketab.di.main.MainViewModelsModule;
import com.pattern.sarketab.di.main.MainModule;
import com.pattern.sarketab.di.month.MonthModule;
import com.pattern.sarketab.di.month.MonthViewModelsModule;
import com.pattern.sarketab.di.pay.PayModule;
import com.pattern.sarketab.di.pay.PayViewModelsModule;
import com.pattern.sarketab.ui.ActivityChange_name;
import com.pattern.sarketab.ui.ActivityEzdevaj;
import com.pattern.sarketab.ui.ActivityGhaeb;
import com.pattern.sarketab.ui.ActivityHajat;
import com.pattern.sarketab.ui.ActivityHamzad;
import com.pattern.sarketab.ui.ActivityResult;
import com.pattern.sarketab.ui.SplashActivity;
import com.pattern.sarketab.ui.auth.AuthActivity;
import com.pattern.sarketab.ui.payment.ActivityPay;
import com.pattern.sarketab.ui.Sarketab.ActivitySarketab;
import com.pattern.sarketab.ui.main.MainActivity;
import com.pattern.sarketab.ui.month.MonthActivity;
import com.pattern.sarketab.ui.payment.ActivityPayMonth;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules ={MainViewModelsModule.class,
                    MainModule.class,
            })
    abstract MainActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MonthViewModelsModule.class,
            MonthModule.class,
            })
    abstract MonthActivity monthActivity();

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class,
            })
    abstract AuthActivity authActivity();

    @ContributesAndroidInjector
    abstract ActivityEzdevaj resultInjection();

    @ContributesAndroidInjector
    abstract ActivityGhaeb ghaebInjection();

    @ContributesAndroidInjector
    abstract ActivityHajat hajatInjection();

    @ContributesAndroidInjector
    abstract ActivityHamzad hamzadInjection();

    @ContributesAndroidInjector
    abstract ActivityChange_name change_nameInjection();

    @ContributesAndroidInjector(
            modules = {PayViewModelsModule.class,
                    PayModule.class,
            })
    abstract ActivityPay payInject();


    @ContributesAndroidInjector
    abstract ActivitySarketab sarketabInject();
    @ContributesAndroidInjector
    abstract SplashActivity splashInject();
    @ContributesAndroidInjector(
            modules = {PayViewModelsModule.class,
                    PayModule.class,
            })
    abstract ActivityPayMonth sarketabPayMonthInject();
    @ContributesAndroidInjector
    abstract ActivityResult ResultInject();



}
