package com.pattern.sarketab.di.auth;

import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.di.ViewModelKey;
import com.pattern.sarketab.ui.auth.AuthViewModel;
import com.pattern.sarketab.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);


}
