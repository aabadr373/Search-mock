package com.example.robustasearch.data

import com.example.robustasearch.data.remote.ApiService
import com.example.robustasearch.data.model.SearchResponse
import io.reactivex.Single

class SearchRepo {

    private val apiService by lazy {
        ApiService.create()
    }

    fun perfomSearch(word: String): Single<SearchResponse> {
       return apiService.perfomSearch(word)
    }
}