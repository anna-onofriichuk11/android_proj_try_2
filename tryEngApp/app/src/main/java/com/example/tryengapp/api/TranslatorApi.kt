<<<<<<< HEAD
package com.example.tryengapp.api

import com.example.tryengapp.Const.TRANSLATE_SITE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http


interface TranslatorApi {

    @POST("translator")
    fun loadTranslation(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("language") lang: String,
        @Query("format") format: String = "plain"
    )

    companion object {
        fun create(): TranslatorApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(TRANSLATE_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(TranslatorApi::class.java)
        }
    }
=======
package com.example.tryengapp.api

import com.example.tryengapp.Const.TRANSLATE_SITE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http


interface TranslatorApi {

    @POST("translator")
    fun loadTranslation(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("language") lang: String,
        @Query("format") format: String = "plain"
    )

    companion object {
        fun create(): TranslatorApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(TRANSLATE_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(TranslatorApi::class.java)
        }
    }
>>>>>>> 38b66a03b864ca564c30ccc18f51636406be220a
}