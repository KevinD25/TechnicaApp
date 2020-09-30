package com.davis.kevin.technicav2.networking

import com.davis.kevin.technicav2.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Endpoints {

    @GET("home")
    fun getHome(): Home

    @GET("clubtext")
    fun getClubText(): Clubtext

    @GET("event")
    fun getEvents(): Call<List<Event>>

    @GET("sponsor")
    fun getPartners(): Call<List<Partner>>

    @GET("sponsor/{id}")
    fun getPartnersByID(@Path("id") id: Int): Partner

    @GET("praesidium")
    fun getPraesidium(): Call<List<Praesidium>>

    @GET("praesidium/{id}")
    fun getPraesidiumByID(@Path("id") id: Int): Praesidium

    @GET("vacature")
    fun getVacatures(): Call<List<Vacature>>
}