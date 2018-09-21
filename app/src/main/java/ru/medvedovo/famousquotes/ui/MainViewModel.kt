package ru.medvedovo.famousquotes.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.medvedovo.famousquotes.model.Quote
import ru.medvedovo.famousquotes.network.QuoteApi

class MainViewModel : ViewModel() {

    private val TAG = MainViewModel::class.java.canonicalName
    private val disposables = CompositeDisposable()

    private val quote: MutableLiveData<Quote> = MutableLiveData()

    fun loadQuote() {
        Log.i(TAG, "loadQuote started")
        disposables.add(QuoteApi.getApi().getRandomQuote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    quote.value = it
                }, {
                    Log.e(TAG, it.message)
                }))
    }

    fun getQuote(): MutableLiveData<Quote> {
        Log.i(TAG, "getQuote started")
        if (quote.value == null) {
            loadQuote()
        }
        return quote
    }

    override fun onCleared() {
        Log.i(TAG, "Disposed")
        super.onCleared()
        disposables.clear()
    }
}