package com.example.newstask.injection.component

import com.example.newstask.ui.NewsListViewModel
import com.example.newstask.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(newsListViewModel: NewsListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule : NetworkModule) : Builder
    }
}