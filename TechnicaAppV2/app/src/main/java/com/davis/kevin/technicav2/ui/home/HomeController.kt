package com.davis.kevin.technicav2.ui.home

import com.davis.kevin.technicav2.models.Home
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class HomeController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)

    private var home : Home = Home()

    suspend fun getHome(): Home = apiService.getHome()
}