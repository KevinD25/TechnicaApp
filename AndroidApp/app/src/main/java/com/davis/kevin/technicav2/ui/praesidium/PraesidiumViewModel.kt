package com.davis.kevin.technicav2.ui.praesidium

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.*
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.FirebaseHandler
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PraesidiumViewModel : ViewModel {

    var id : String? = ""
    var name: String? = ""
    var surname: String? = ""
    var studies: String? = ""
    var image: Bitmap? = null
    var functie: Functie? = null
    var birthday: LocalDate? = null

    constructor() : super()
    constructor(praesidium: Praesidium) : super() {
        this.id = praesidium.id
        this.name = praesidium.name
        this.surname = praesidium.surname
        this.birthday = praesidium.birthday
        this.studies = formatStudies(praesidium.studies)
        this.functie = praesidium.functie
        this.image = praesidium.image
    }

    fun getViewImage(): BitmapDrawable {
        return BitmapDrawable(image)
    }

    fun getArray() : LiveData<List<Praesidium>>{
        return FirebaseHandler.praesidiumList
    }

    fun getPraesidiumFunctie() : String {
        return Functie.enumToString(this.functie)
    }

    private fun formatStudies(studies: String?) : String {
        if (studies!!.length > 25) {   // Kijkt na of de string te lang is
            val words: List<String> = studies.split(" ")   // Plaats alle woorden apart in een lijst
            var line = ""
            var result = ""
            for (word: String in words) {
                if (line.length + word.length < 28) {
                    line = "$line $word"
                } else { // Als de lijn te lang word dan breekt hij af
                    result += line
                    line = " \n $word"
                }
            }
            return result + line
        } else { // Stuurt hem door als de string niet te lang is
            return studies
        }
    }

    fun getViewBirthday(): String {
        if (this.birthday == null) return ""
        return this.birthday!!.format(DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy"))
    }

}