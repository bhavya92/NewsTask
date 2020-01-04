package com.example.newstask.ui

import androidx.lifecycle.MutableLiveData
import com.example.newstask.base.BaseViewModel

class NewsViewModel : BaseViewModel() {
    private var newsTitle: MutableLiveData<String> = MutableLiveData()

    fun bind(news: String) {
        newsTitle.value = news
    }

    fun getNewsTitle(): MutableLiveData<String>? {
        return newsTitle
    }
}