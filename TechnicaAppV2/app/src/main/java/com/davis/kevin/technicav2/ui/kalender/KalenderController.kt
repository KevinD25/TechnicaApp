package com.davis.kevin.technicav2.ui.kalender

import com.davis.kevin.technicav2.models.Event
import com.davis.kevin.technicav2.networking.APIServiceBuilder
import com.davis.kevin.technicav2.networking.Endpoints

class KalenderController {
    private val apiService  = APIServiceBuilder.buildService(Endpoints::class.java)

    private var events : MutableList<Event> = mutableListOf(Event())

    suspend fun getEvents() : List<Event> = apiService.getEvents()
}