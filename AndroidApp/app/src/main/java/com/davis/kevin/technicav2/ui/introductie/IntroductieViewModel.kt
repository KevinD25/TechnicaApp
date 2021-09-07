package com.davis.kevin.technicav2.ui.introductie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Introductie
import com.davis.kevin.technicav2.networking.FirebaseHandler

class IntroductieViewModel : ViewModel {

    var id : String? = ""
    var text : String? = ""

    constructor() : super()
    constructor(clubtext: Introductie): super() {
        this.id = clubtext.id
        this.text = clubtext.clubText
    }

    fun getArray() : MutableLiveData<Introductie>{
        return FirebaseHandler.clubText
    }

}