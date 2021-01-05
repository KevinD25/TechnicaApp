package com.davis.kevin.technicav2.ui.praesidium

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

   /* fun getFunctieName() : Drawable {
        when(functie){
            "Praeses" -> return images?.get("praeses")!!
            "Vice-praeses" -> return images?.get("vicepraeses")!!
            "S.O.C." -> return images?.get("soc")!!
            "Quaestor" -> return images?.get("quaestor")!!
            "Ere-Lid" -> return images?.get("erelid")!!
            "Peter" -> return images?.get("peter")!!
            "Redactor" -> return images?.get("redactor")!!
            "Media" ->  return images?.get("media")!!
            "Feest" -> return images?.get("feest")!!
            "Zedenmeester" -> return images?.get("zeden")!!
            "Schachtenmeester" -> return images?.get("meester")!!
            "Meter" -> return images?.get("meter")!!
            "Meterke" -> return images?.get("meterke")!!
            "Schachtentemmer" -> return images?.get("temmer")!!
            "P.R." -> return images?.get("pr")!!
            "Ab-Actis" -> return images?.get("abactis")!!
            "Ere-Praeses" -> return images?.get("erepraeses")!!
            "Cantor" -> return images?.get("cantor")!!
        }
        return images?.get("praeses")!!
    }*/

    fun getArray() : MutableList<Praesidium>{
        return FirebaseHandler.praesidiumList
    }

    fun getArrayList(): MutableLiveData<List<Praesidium>> {
        return Repository.praesidiumMutableLiveData
    }

}