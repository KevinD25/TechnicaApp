package com.davis.kevin.technicav2.networking

import com.davis.kevin.technicav2.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Endpoints {


    @GET("Clubtext")
    suspend fun getClubText(): Clubtext

    @GET("Event")
    suspend fun getEvents(): List<Event>

    @GET("Partner")
    suspend fun getPartners(): List<Partner>

    @GET("Praesidium")
    suspend fun getPraesidium(): List<Praesidium>

    @GET("Vacature")
    suspend fun getVacatures(): List<Vacature>
}