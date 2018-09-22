package ru.medvedovo.famousquotes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.medvedovo.famousquotes.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initObservers()
        initHandlers()
    }

    private fun initObservers() {
        model.getQuote().observe(this, Observer {
            text_quote_main.text = it.quoteText
            text_quote_author.text = if (!it.quoteAuthor.isNullOrEmpty())
                String.format(Locale.getDefault(), getString(R.string.quote_author), it.quoteAuthor)
            else ""
        })
    }

    private fun initHandlers() {
        swipe_layout.setOnRefreshListener {
            model.loadQuote()
            swipe_layout.isRefreshing = false
        }

        fab_refresh.setOnClickListener {
            model.loadQuote()
        }

        fab_share.setOnClickListener {
            val quote = model.getQuote().value ?: return@setOnClickListener

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                if (quote.quoteAuthor.isNullOrEmpty()) {
                    putExtra(Intent.EXTRA_TEXT,
                            String.format(Locale.getDefault(), getString(R.string.quote_share_format_noauthor), quote.quoteText))
                } else {
                    putExtra(Intent.EXTRA_TEXT,
                            String.format(Locale.getDefault(), getString(R.string.quote_share_format_author), quote.quoteText, quote.quoteAuthor))

                }
                type = "text/plain"
            }
            startActivity(shareIntent)
        }
    }
}
