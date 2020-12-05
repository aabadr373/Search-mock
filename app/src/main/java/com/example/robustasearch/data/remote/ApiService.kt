package com.example.robustasearch.data.remote

import com.example.robustasearch.data.model.SearchResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/")
    fun perfomSearch(@Query("response") word: String): Single<SearchResponse>

    companion object {
        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://167fd646-bfb1-4d41-ae47-ad6a5edeb23e.mock.pstmn.io")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}