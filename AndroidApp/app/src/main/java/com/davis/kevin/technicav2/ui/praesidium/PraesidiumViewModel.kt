package com.davis.kevin.technicav2.ui.praesidium

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.*
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.davis.kevin.technicav2.repository.Repository
import com.google.common.io.Resources


class PraesidiumViewModel : ViewModel {


    var id : String? = ""
    var name: String? = ""
    var surname: String? = ""
    var birthday: String? = ""
    var studies: String? = ""
    var functie: String? = ""
    var imagelink: Bitmap? = null
    var list : LiveData<List<Praesidium>>? = null
    //var images : MutableMap<String, Drawable>? = HashMap()


    constructor() : super()
    constructor(
        praesidium: Praesidium
    ) : super() {
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

}