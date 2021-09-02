package com.davis.kevin.technicav2.ui.praesidium

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.*
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.FirebaseHandler


class PraesidiumViewModel : ViewModel {

    var id : String? = ""
    var name: String? = ""
    var surname: String? = ""
    var birthday: String? = ""
    var studies: String? = ""
    var functie: Functie? = null
    var imagelink: Bitmap? = null
    var list : LiveData<List<Praesidium>>? = null
    //var images : MutableMap<String, Drawable>? = HashMap()

    constructor() : super()
    constructor(praesidium: Praesidium) : super() {
        this.id = praesidium.id
        this.name = praesidium.name
        this.surname = praesidium.surname
        this.birthday = praesidium.birthday
        this.studies = praesidium.studies
        this.functie = praesidium.functie
        this.imagelink = praesidium.imageLink
       // this.images = praesidium.images
    }

    fun getImage(): BitmapDrawable {
        return BitmapDrawable(imagelink)
    }

    fun getArray() : LiveData<List<Praesidium>>{
        return FirebaseHandler.praesidiumList
    }

    fun getPraesidiumFunctie() : String? {
        return Functie.EnumToString(this.functie)
    }

}