package com.example.newstask.data.service

import com.example.newstask.data.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getArticles(@Query("country") country : String, @Query("apiKey") key : String) : Observable<Result>
}