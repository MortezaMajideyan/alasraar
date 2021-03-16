package com.pattern.sarketab.di.month;

import androidx.lifecycle.ViewModel;

import com.pattern.sarketab.di.ViewModelKey;
import com.pattern.sarketab.ui.main.MainViewModel;
import com.pattern.sarketab.ui.month.MonthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MonthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MonthViewModel.class)
    public abstract ViewModel bindMonthViewModel(MonthViewModel viewModel);
}
