package com.fazv.data.api.retrofit

import com.fazv.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    fun <T> getServiceApi(apiClass: Class<T>): T =
        getBuild().create(apiClass)

    fun getBuild(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.CURRENCYLAYER_URL_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

    private fun getHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .addNetworkInterceptor(getHttpInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply {
                level = getInterceptorLevel()
            }

    private fun getHttpInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url()

        val url = httpUrl
            .newBuilder()
            .addQueryParameter(ACCESS_KEY_QUERY, BuildConfig.CURRENCYLAYER_ACCESS_KEY)
            .build()

        val requestBuilder = originalRequest
            .newBuilder()
            .url(url)

        chain.proceed(requestBuilder.build())
    }

    private fun getInterceptorLevel() =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

    companion object {
        const val TIMEOUT = 60L
        const val ACCESS_KEY_QUERY = "access_key"
    }
}