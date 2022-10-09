package com.davis.kevin.technicav2.networking

import com.davis.kevin.technicav2.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    val apiService: Endpoints

    init {
        val client = OkHttpClient.Builder().build()
        apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Endpoints::class.java)
    }
}