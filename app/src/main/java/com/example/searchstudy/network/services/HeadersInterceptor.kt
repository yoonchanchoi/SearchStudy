package com.example.searchstudy.network.services

import com.example.searchstudy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeadersInterceptor @Inject constructor(
//    private val prefs: Prefs
) : Interceptor {

    companion object {
        private const val CLIENT_ID = BuildConfig.CLIENT_ID
        private const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }
}