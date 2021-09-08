package com.davis.kevin.technicav2.ui.vacatures

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.networking.FirebaseHandler

class VacatureSponsor {

    var id: String? = ""
    var name: String? = ""
    var image: Bitmap? = null

    constructor() : super()
    constructor(partner: Partner) : super() {
        this.id = partner.id
        this.name = partner.name
        this.image = partner.image
    }

    fun getArray(): MutableLiveData<List<Partner>> {
        return FirebaseHandler.sponsorList
    }
}