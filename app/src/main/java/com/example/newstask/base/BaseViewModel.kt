package com.example.newstask.base

import androidx.lifecycle.ViewModel
import com.example.newstask.injection.component.DaggerViewModelInjector
import com.example.newstask.ui.NewsListViewModel
import com.example.newstask.injection.module.NetworkModule
import com.example.newstask.injection.component.ViewModelInjector

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is NewsListViewModel -> injector.inject(this)
        }
    }
}