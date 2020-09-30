package com.davis.kevin.technicav2.networking

import android.content.Context
import android.provider.SyncStateContract
import com.davis.kevin.technicav2.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIServiceBuilder {

    private fun getClient(): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor((HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)))
        .build()

    private fun getRetrofitClient(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun <T> buildService(service: Class<T>): T{
        return getRetrofitClient().create(service)
    }



}