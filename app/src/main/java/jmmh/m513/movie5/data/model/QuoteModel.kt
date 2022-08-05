package jmmh.m513.movie5.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)