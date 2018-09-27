package ru.medvedovo.famousquotes

import org.junit.Test

import org.junit.Assert.*
import ru.medvedovo.famousquotes.model.Quote
import java.util.*

class QuoteModelTest {
    private val quoteText = "Test quote."
    private val quoteAuthor = "Test author"

    @Test
    fun QuoteFormatWithAuthor_isCorrect() {
        val model = Quote(quoteText, quoteAuthor)
        val expected = String.format(Locale.getDefault(), "«%s», — %s", quoteText.removeSuffix("."), quoteAuthor)
        assertEquals(model.getShareQuote(), expected)
    }

    @Test
    fun QuoteFormatNoAuthor_isCorrect() {
        val model = Quote(quoteText)
        val expected = String.format(Locale.getDefault(), "«%s»", quoteText.removeSuffix("."))
        assertEquals(model.getShareQuote(), expected)
    }
}
