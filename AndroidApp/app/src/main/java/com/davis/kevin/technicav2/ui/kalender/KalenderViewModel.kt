package com.davis.kevin.technicav2.ui.kalender

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Evenement
import com.davis.kevin.technicav2.networking.FirebaseHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class KalenderViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var fbLink: String? = ""
    var formsLink: String? = ""
    var location: String? = ""
    var price: Int? = null
    var description: String? = ""
    var image: Bitmap? = null
    var date: LocalDateTime? = null

    constructor() : super() {}
    constructor(event: Evenement) : super() {
        this.id = event.id
        this.name = event.name
        this.formsLink = event.formsLink
        this.location = event.location
        this.price = event.price!!.toInt()
        this.description = event.description
        this.fbLink = event.fbLink
        this.image = event.image
        this.date = event.date
    }

    fun getArrayList(): MutableLiveData<List<Evenement>> {
        return FirebaseHandler.eventList
    }

    fun getViewImage(): BitmapDrawable {
        return BitmapDrawable(image)
    }

    fun getViewDate(): String {
        if (date == null) return ""
        return date!!.format(DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy | HH:mm"))
    }

    fun getViewPrice(): String? {
        return "BE27 7310 2609 3173 - €${price.toString()}"
    }
}