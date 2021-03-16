package com.pattern.sarketab;


import com.pattern.sarketab.di.main.MainModule;
import com.pattern.sarketab.di.main.MainViewModelsModule;
import com.pattern.sarketab.di.month.MonthModule;
import com.pattern.sarketab.di.month.MonthViewModelsModule;
import com.pattern.sarketab.ui.main.MainActivity;
import com.pattern.sarketab.ui.month.MonthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule1 {

    @ContributesAndroidInjector(
            modules ={MonthViewModelsModule.class,
                    MonthModule.class,
            })
    abstract MonthActivity contributeAuthActivity();






}
