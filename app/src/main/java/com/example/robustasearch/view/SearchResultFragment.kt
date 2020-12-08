package com.example.robustasearch.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.robustasearch.data.SearchAdapter
import com.example.robustasearch.data.SearchRepo
import com.example.robustasearch.databinding.FragmentSearchResultBinding
import com.example.robustasearch.viewmodel.SearchViewModel
import com.example.robustasearch.viewmodel.VmFactory


/**
 * A simple [Fragment] subclass.
 * Use the [SearchResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchResultFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var viewModelFactory: VmFactory

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModelFactory =
                VmFactory(SearchRepo())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)
                .get(SearchViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSearch.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(binding.rvSearch.getContext(),
                layoutManager.orientation)
        binding.rvSearch.addItemDecoration(dividerItemDecoration)
        val adapter = SearchAdapter()
        binding.rvSearch.adapter = adapter

        viewModel.searchText.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.searchPrefix = it
                Log.v("Search", "searchPrefix $it")
            }
        })


        viewModel.searchResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.dataSet = it
                Log.v("dwqdqd", "${it.size}")
            }
        })


        viewModel.scrollToTop.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    scrollToTop()
                    viewModel.onFabClicked(false)
                }
            }
        })


        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) binding.pbSearch.visibility = VISIBLE
                else binding.pbSearch.visibility = GONE
            }
        })


    }

    fun scrollToTop() {
        val layoutManager = binding.rvSearch.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(0, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchResultFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SearchResultFragment().apply {
                }
    }
}