package com.davis.kevin.technicav2.ui.praesidium

import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class PraesidiumController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)
    private var praesidiumList : MutableList<Praesidium> = mutableListOf(Praesidium())
    private var praesidium : Praesidium = Praesidium()

    suspend fun getPraesidiumList() : List<Praesidium> = apiService.getPraesidium()
    suspend fun getPraesidium(id : Int) : Praesidium = apiService.getPraesidiumByID(id) //temp
}