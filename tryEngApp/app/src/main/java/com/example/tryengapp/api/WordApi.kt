package com.example.tryengapp.api

import com.example.tryengapp.api.model.WordData
import com.example.tryengapp.Const
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface WordApi {

    @GET("{words}")
    fun loadDefinition(@Path("word") word: String): Observable<WordData>

    companion object {
        private var client : OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(WordInterceptorApi())
            .build()

        fun create(): WordApi {
            val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Const.BASE_URL)
                .build()
            return retrofit.create(WordApi::class.java)
        }
    }
}