package ru.medvedovo.famousquotes.network

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.medvedovo.famousquotes.model.Quote

interface QuoteApi {

    @GET("?method=getQuote&lang=ru&format=json")
    fun getRandomQuote(): Observable<Quote>

    companion object Factory {

        private const val BASE_URL = "http://api.forismatic.com/api/1.0/"
        private var api: QuoteApi? = null

        fun getApi(): QuoteApi {
            if (api != null) {
                return api as QuoteApi
            }

            val httpLogging = HttpLoggingInterceptor()
            httpLogging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(httpLogging)

            httpClient.addInterceptor {
                val request = it.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                it.proceed(request)
            }

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .build()
            api = retrofit.create(QuoteApi::class.java)

            return api as QuoteApi
        }
    }
}