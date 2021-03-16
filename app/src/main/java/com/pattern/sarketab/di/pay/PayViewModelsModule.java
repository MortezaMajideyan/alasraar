package com.pattern.sarketab.di.pay;

import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.di.ViewModelKey;
import com.pattern.sarketab.ui.main.MainViewModel;
import com.pattern.sarketab.ui.payment.PayViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PayViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel.class)
    public abstract ViewModel bindMainViewModel(PayViewModel viewModel);
}
