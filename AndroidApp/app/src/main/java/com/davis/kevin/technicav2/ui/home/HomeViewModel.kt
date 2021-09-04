package com.davis.kevin.technicav2.ui.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Evenement
import com.davis.kevin.technicav2.networking.FirebaseHandler
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var fbLink: String? = ""
    var image: Bitmap? = null
    var date: LocalDate? = null

    constructor() : super() {}
    constructor(event: Evenement) : super() {
        this.id = event.id
        this.name = event.name
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
        return date!!.format(DateTimeFormatter.ofPattern("dd/mm/yyyy"))
    }
}