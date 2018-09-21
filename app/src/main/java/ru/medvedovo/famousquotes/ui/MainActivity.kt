package ru.medvedovo.famousquotes.ui

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
        model.getQuote().observe(this, Observer {
            text_quote_main.text = it.quoteText
            text_quote_author.text = if (!it.quoteAuthor.isNullOrEmpty())
                String.format(Locale.getDefault(), getString(R.string.quote_author), it.quoteAuthor)
                    else ""
        })

        fab_refresh.setOnClickListener {
            model.loadQuote()
        }
    }
}
