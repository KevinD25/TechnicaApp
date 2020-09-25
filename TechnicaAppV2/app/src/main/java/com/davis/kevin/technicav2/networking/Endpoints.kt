package com.davis.kevin.technicav2.networking

import com.davis.kevin.technicav2.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Endpoints {

    @GET("home")
    suspend fun getHome(): Home

    @GET("clubtext")
    suspend fun getClubText(): Clubtext

    @GET("event")
    suspend fun getEvents(): List<Event>

    @GET("sponsor")
    suspend fun getPartners(): List<Partner>

    @GET("sponsor/{id}")
    suspend fun getSponsorByID(): Partner

    @GET("praesidium")
    suspend fun getPraesidium(): List<Praesidium>

    @GET("praesidium/{id}")
    suspend fun getPraesidiumByID(): Praesidium

    @GET("vacature")
    suspend fun getVacatures(): List<Vacature>
}