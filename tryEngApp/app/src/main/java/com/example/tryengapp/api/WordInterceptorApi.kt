package com.example.tryengapp.api

import com.example.tryengapp.Const
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WordInterceptorApi : Interceptor {
    override fun intercept(ch: Interceptor.Chain): Response {
        val original : Request = ch.request()
        val request = original.newBuilder()
            .header(Const.WORD_HOST_NAME, Const.WORD_HOST_VALUE)
            .header(Const.WORD_KEY_NAME, Const.WORD_KEY_VALUE)
            .build()
        return ch.proceed(request)
    }
}