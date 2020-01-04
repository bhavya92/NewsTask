package com.example.newstask.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.newstask.R
import com.example.newstask.base.BaseViewModel
import com.example.newstask.data.model.Article
import com.example.newstask.data.service.NewsService
import com.example.newstask.data.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel : BaseViewModel(){
    @Inject
    lateinit var newsService: NewsService

    val titleList : ArrayList<String> = ArrayList()
    val newsListAdapter = NewsAdapter(titleList)
    val loadingVisibility : MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription : Disposable

    init {
        loadNews()
    }

    private fun loadNews() {
        subscription = newsService.getArticles("in","90c904c71c944a1389403504d9894972")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveNewsListStart() }
            .doOnTerminate { onRetrieveNewsListFinish() }
            .subscribe(
                { result -> onRetrieveNewsListSuccess( result ) },
                { onRetrieveNewsListError() }
            )
    }

    private fun onRetrieveNewsListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveNewsListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveNewsListSuccess(newsList: Result) {

        val articles : Array<Article> = newsList.articles
        for(i in articles) {
            titleList.add(i.title)
        }
        newsListAdapter.updateNewsList(titleList)
    }

    private fun onRetrieveNewsListError() {
        errorMessage.value = R.string.news_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}