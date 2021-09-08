package com.davis.kevin.technicav2.ui.sponsors

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.networking.FirebaseHandler

class SponsorsViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var description: String? = ""
    var website: String? = ""
    var image: Bitmap? = null
    var priority: Long? = null

    constructor() : super()
    constructor(
        partner: Partner
    ) : super() {
        this.id = partner.id
        this.name = partner.name
        this.description = partner.description
        this.website = partner.website
        this.image = partner.image
        this.priority = partner.priority
    }

    fun getArray(): MutableLiveData<List<Partner>> {
        return FirebaseHandler.sponsorList
    }

    fun getViewImage(): BitmapDrawable {
        return BitmapDrawable(image)
    }

}