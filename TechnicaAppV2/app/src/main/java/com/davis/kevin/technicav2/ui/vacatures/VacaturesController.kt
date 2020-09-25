package com.davis.kevin.technicav2.ui.vacatures

import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class VacaturesController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)
    private var vacatures : MutableList<Vacature> = mutableListOf(Vacature())

    suspend fun getVacatures() : List<Vacature> = apiService.getVacatures()
}