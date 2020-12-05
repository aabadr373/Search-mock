package com.example.robustasearch.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.robustasearch.R
import com.example.robustasearch.data.SearchRepo
import com.example.robustasearch.databinding.ActivityMainBinding
import com.example.robustasearch.viewmodel.SearchViewModel
import com.example.robustasearch.viewmodel.VmFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: SearchViewModel
    private lateinit var viewModelFactory: VmFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModelFactory =
            VmFactory(SearchRepo())
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<SearchResultFragment>(
                    R.id.fl_container
                )
            }
        }

        binding.fab.setOnClickListener {
            viewModel.onFabClicked(true)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    binding.tvWelcome.visibility = GONE
                    viewModel.performSearch(s.toString())
                    Log.v("Search", "performSearch $s")
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}