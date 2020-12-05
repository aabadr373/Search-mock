package com.example.robustasearch.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.robustasearch.databinding.ItemSearchBinding

class SearchAdapter() :
        RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var dataSet = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    var searchPrefix: String = ""
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(private val itemSearchBinding: ItemSearchBinding) : RecyclerView.ViewHolder(itemSearchBinding.root) {

        fun bind(searchPrefix: String, searchItem: String) {
            itemSearchBinding.tvPrefix.text = searchPrefix
            itemSearchBinding.tvName.text = searchItem.substring(searchPrefix.length)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            itemBinding
        )
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchPrefix, dataSet[position])

    }
}