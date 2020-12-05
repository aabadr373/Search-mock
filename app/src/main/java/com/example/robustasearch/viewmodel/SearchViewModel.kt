package com.example.robustasearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.robustasearch.data.SearchRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(searchRepo: SearchRepo) : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    var repo = searchRepo
    private val mutableSearchResponse = MutableLiveData<List<String>>()

    val searchResponse: LiveData<List<String>> get() = mutableSearchResponse

    private val searchTextLivedata = MutableLiveData<String>()

    val searchText: LiveData<String> get() = searchTextLivedata

    private val mutableScrollToTop = MutableLiveData<Boolean>()

    val scrollToTop: LiveData<Boolean> get() = mutableScrollToTop

    private val mutableLoading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean> get() = mutableLoading

    fun performSearch(text: String) {
        mutableLoading.value = true
        searchTextLivedata.value = text
        compositeDisposable.add(
            repo.perfomSearch(text).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ response ->
                    run {
                        mutableSearchResponse.value = response.response
                        mutableLoading.value = false

                    }
                },
                    { error -> mutableLoading.postValue(false) })
        )
    }

    fun onFabClicked(clicked: Boolean) {
        mutableScrollToTop.value = clicked
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}