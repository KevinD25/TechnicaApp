package com.davis.kevin.technicav2.ui.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.models.Evenement
import com.davis.kevin.technicav2.networking.FirebaseHandler
import kotlinx.coroutines.flow.merge
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var fbLink: String? = ""
    var image: Bitmap? = null
    var date: LocalDateTime? = null

    constructor() : super()
    constructor(event: Evenement) : super() {
        this.id = event.id
        this.name = event.name
        this.fbLink = event.fbLink
        this.image = event.image
        this.date = event.date
    }

    fun getArrayList(): MutableLiveData<List<Evenement>> {
        val upcommingEvents = MutableLiveData<List<Evenement>>()
        if (FirebaseHandler.eventList.value!!.size > 3) {
            upcommingEvents.value = FirebaseHandler.eventList.value!!.take(3)
        } else {
            upcommingEvents.value = FirebaseHandler.eventList.value
        }
        return upcommingEvents
    }

    fun getViewImage(): BitmapDrawable {
        return BitmapDrawable(image)
    }

    fun getViewDate(): String {
        if (date == null) return ""
        return date!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm"))
    }
}