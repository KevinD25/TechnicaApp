package com.davis.kevin.technicav2.ui.clublied

import com.davis.kevin.technicav2.models.Clubtext
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class ClubliedController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)
    private var Clubtext : Clubtext = Clubtext()

    suspend fun getClubtext() : Clubtext = apiService.getClubText()
}