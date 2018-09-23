package ru.medvedovo.famousquotes.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Quote(
        @SerializedName("quoteText") var quoteText: String? = null,
        @SerializedName("quoteAuthor") var quoteAuthor: String? = null,
        @SerializedName("senderName") var senderName: String? = null,
        @SerializedName("senderLink") var senderLink: String? = null,
        @SerializedName("quoteLink") var quoteLink: String? = null
) {
    fun getShareQuote(): String {
        val quoteTextNoDot = quoteText?.removeSuffix(".")
        return if (quoteAuthor.isNullOrEmpty()) {
            String.format(Locale.getDefault(), "«%s»", quoteTextNoDot)
        } else {
            String.format(Locale.getDefault(), "«%s», — %s", quoteTextNoDot, quoteAuthor)
        }
    }
}