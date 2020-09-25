package com.davis.kevin.technicav2.ui.onzeclub

import com.davis.kevin.technicav2.models.Clubtext
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class OnzeclubController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)
    private var clubtext : Clubtext = Clubtext()

    suspend fun getClubtext() : Clubtext = apiService.getClubText()
}