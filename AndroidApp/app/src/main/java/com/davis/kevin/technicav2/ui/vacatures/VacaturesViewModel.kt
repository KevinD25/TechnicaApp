package com.davis.kevin.technicav2.ui.vacatures

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.FirebaseHandler

class VacaturesViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var description: String? = ""
    var link: String? = ""
    var partner: VacatureSponsor? = null

    constructor() : super() {}
    constructor(vacature: Vacature) : super() {
        this.id = vacature.id
        this.name = vacature.name
        this.description = vacature.description
        this.link = vacature.link
    }

    fun getArray(): MutableLiveData<List<Vacature>> {
        return FirebaseHandler.vacancieList
    }

    fun getViewImage(): BitmapDrawable {
        val image = this.partner!!.image?.let { scale(it) }
        return BitmapDrawable(image)
    }

    private fun scale(realImage: Bitmap): Bitmap? {
        return Bitmap.createScaledBitmap(realImage, 1024, 1024, false)
    }

}