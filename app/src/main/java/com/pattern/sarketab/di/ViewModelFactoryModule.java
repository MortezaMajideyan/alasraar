package com.pattern.sarketab.di;

import androidx.lifecycle.ViewModelProvider;


import com.pattern.sarketab.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
