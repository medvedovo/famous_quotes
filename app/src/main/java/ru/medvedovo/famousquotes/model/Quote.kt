package ru.medvedovo.famousquotes.model

import com.google.gson.annotations.SerializedName

data class Quote(
        @SerializedName("quoteText") var quoteText: String? = null,
        @SerializedName("quoteAuthor") var quoteAuthor: String? = null,
        @SerializedName("senderName") var senderName: String? = null,
        @SerializedName("senderLink") var senderLink: String? = null,
        @SerializedName("quoteLink") var quoteLink: String? = null
)