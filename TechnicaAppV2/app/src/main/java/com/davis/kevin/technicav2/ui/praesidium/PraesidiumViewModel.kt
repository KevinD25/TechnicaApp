package com.davis.kevin.technicav2.ui.praesidium

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Praesidium

class PraesidiumViewModel : ViewModel {


    var id= ""
    var name = ""
    var surname = ""
    var birthday = ""
    var studies = ""
    var functie = ""



    /*private val _text = MutableLiveData<String>().apply {
        value = "This is share Fragment"
    }
    val text: LiveData<String> = _text*/

    constructor() : super()
    constructor(
        praesidium:Praesidium
    ) : super() {
        this.id = praesidium.id
        this.name = praesidium.name
        this.surname = praesidium.surname
        this.birthday = praesidium.birthday
        this.studies = praesidium.studies
        this.functie = praesidium.functie
    }

    var arrayListMutableLiveData = MutableLiveData<ArrayList<PraesidiumViewModel>>()

    var arrayList = ArrayList<PraesidiumViewModel>()

    fun getArrayList() : MutableLiveData<ArrayList<PraesidiumViewModel>>{
        val praesidium1 = Praesidium(id="1", name="Davis", surname = "Kevin", birthday = "25/11/1993", studies ="Elektronica-ICT", functie = "Praeses")
        val praesidium2 = Praesidium(id="2", name="Brusten", surname = "Björn", birthday = "26/11/1993", studies ="Energiemanagement", functie = "Vice-Praeses")
        val praesidium3 = Praesidium(id="3", name="Van Mol", surname="Robin", birthday = "idk lol", studies="Electromechanica", functie = "Quaestor")

        val praesidiumViewModel1 : PraesidiumViewModel = PraesidiumViewModel(praesidium1)
        val praesidiumViewModel2 : PraesidiumViewModel = PraesidiumViewModel(praesidium2)
        val praesidiumViewModel3 : PraesidiumViewModel = PraesidiumViewModel(praesidium3)

        arrayList!!.add(praesidiumViewModel1)
        arrayList!!.add(praesidiumViewModel2)
        arrayList!!.add(praesidiumViewModel3)

        arrayListMutableLiveData.value = arrayList
        return arrayListMutableLiveData
    }

}