package com.davis.kevin.technicav2.ui.sponsors

import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class SponsorsController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)
    private var sponsors : MutableList<Partner> = mutableListOf(Partner())
    private var sponsor : Partner = Partner()

    suspend fun getSponsors() : List<Partner> = apiService.getPartners()
    suspend fun getSponsor(id : Int) : Partner = apiService.getPartnersByID(id)
}