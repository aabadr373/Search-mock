package com.example.robustasearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.robustasearch.data.SearchRepo

class VmFactory (private val repo: SearchRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

     }
}
