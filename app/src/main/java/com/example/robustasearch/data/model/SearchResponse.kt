package com.example.robustasearch.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("data")
	val data: Boolean? = null,

	@field:SerializedName("response")
	val response: List<String>? = null
)
